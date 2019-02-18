package com.borderdev.domain.usecases.episode

import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Flowable
import io.reactivex.Observable

class GetEpisodeByType(
        private val repository: PodcastRepository,
        private val schedulerProvider: BaseScheduler
): BaseUseCase<GetEpisodeByType.EpisodesRequestValues, GetEpisodeByType.EpisodesResultValues>() {

    override fun execute(requestValues: EpisodesRequestValues): EpisodesResultValues {
        return EpisodesResultValues(
                repository.getEpisodesByType(requestValues.episodeType)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        )
    }

    class EpisodesRequestValues(val episodeType: EpisodeType): RequestValues
    class EpisodesResultValues(val observable: Flowable<List<Episode>>): ResultValues
}