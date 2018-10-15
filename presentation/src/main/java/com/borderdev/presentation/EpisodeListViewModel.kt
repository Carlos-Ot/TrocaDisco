package com.borderdev.presentation

import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import com.borderdev.domain.usecases.episode.GetEpisodeByType
import io.reactivex.Flowable

class EpisodeListViewModel(private val getEpisodesByType: GetEpisodeByType) : BaseViewModel<List<Episode>>() {

    var episodeType: EpisodeType = EpisodeType.DEFAULT

    override fun fetchData() {
        state.postValue(ViewState(ViewState.Status.LOADING))

        val useCaseParam = GetEpisodeByType.EpisodesRequestValues(EpisodeType.PODCAST)
        val flowable: Flowable<List<Episode>> = getEpisodesByType.execute(useCaseParam).flowable

        disposables.add(
                flowable.subscribe(
                        { episodes ->
                            state.postValue(ViewState(status = ViewState.Status.SUCCESS, data = episodes))
                        },
                        { error ->
                            state.postValue(ViewState(status = ViewState.Status.ERROR, error = error))
                        }
                )
        )

    }

}