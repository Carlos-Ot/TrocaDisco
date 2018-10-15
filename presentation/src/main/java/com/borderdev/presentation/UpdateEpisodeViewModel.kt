package com.borderdev.presentation

import androidx.lifecycle.*
import com.borderdev.domain.model.Episode
import com.borderdev.domain.usecases.episode.UpdateEpisode
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable

class UpdateEpisodeViewModel(private val updateEpisode: UpdateEpisode): ViewModel(), LifecycleObserver {

    protected val disposables = CompositeDisposable()

    protected val state: MutableLiveData<ViewState<Episode>> = MutableLiveData()

    var episode: Episode? = null

    fun getState(): LiveData<ViewState<Episode>> {
        return state
    }

    fun updateEpisode(episode: Episode) {
        state.postValue(ViewState(status = ViewState.Status.LOADING))

        val useCaseParam: UpdateEpisode.UpdateRequestValues = UpdateEpisode.UpdateRequestValues(episode)
        val completable: Completable = updateEpisode.execute(useCaseParam).completable

        disposables.add(
                completable.subscribe(
                        {
                            state.postValue(ViewState(status = ViewState.Status.SUCCESS, data = episode))
                        },
                        { error ->
                            state.postValue(ViewState(status = ViewState.Status.ERROR, error = error))
                        }
                )
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchIfNeeded() {
        if (state.value == null) {
            episode?.let {
                updateEpisode(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}