package com.borderdev.domain.usecases.episode

import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Single

class GetEpisode(
        private val repository: PodcastRepository,
        private val schedulerProvider: BaseScheduler
): BaseUseCase<GetEpisode.EpisodeRequestValues, GetEpisode.EpisodeResultValues>() {

    override fun execute(requestValues: EpisodeRequestValues): EpisodeResultValues {
        return EpisodeResultValues(
                repository.getEpisode(requestValues.episodeId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
        )
    }

    class EpisodeRequestValues(val episodeId: Long): RequestValues
    class EpisodeResultValues(val single: Single<Episode>): ResultValues
}