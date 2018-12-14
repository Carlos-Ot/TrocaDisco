package com.borderdev.domain.usecases.episode

import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Observable

class GetEpisodes(
        private val repository: PodcastRepository,
        private val schedulerProvider: BaseScheduler
): BaseUseCase<GetEpisodes.EpisodesRequestValues, GetEpisodes.EpisodesResultValues>() {

    override fun execute(requestValues: EpisodesRequestValues): EpisodesResultValues {
        return EpisodesResultValues(
                repository.getEpisodes()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
        )
    }

    class EpisodesRequestValues: RequestValues
    class EpisodesResultValues(val observable: Observable<List<Episode>>): ResultValues

}