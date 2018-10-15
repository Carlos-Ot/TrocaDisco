package com.borderdev.presentation

import android.view.View
import com.borderdev.domain.model.Episode
import com.borderdev.domain.usecases.episode.GetEpisode
import io.reactivex.Single

class EpisodeViewModel(private val getEpisode: GetEpisode): BaseViewModel<Episode>() {

    var episodeId: Long = 0

    override fun fetchData() {
        state.postValue(ViewState(status = ViewState.Status.LOADING))

        val useCaseParam = GetEpisode.EpisodeRequestValues(episodeId)
        val single: Single<Episode> = getEpisode.execute(useCaseParam).single

        disposables.add(
                single.subscribe(
                        { episode ->
                            state.postValue(ViewState(status = ViewState.Status.SUCCESS, data = episode))
                        },
                        { error ->
                            state.postValue(ViewState(status = ViewState.Status.ERROR, error = error))
                        }
                )
        )
    }
}