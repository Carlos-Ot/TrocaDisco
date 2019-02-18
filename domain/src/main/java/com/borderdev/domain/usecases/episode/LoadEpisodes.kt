package com.borderdev.domain.usecases.episode

import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Completable

class LoadEpisodes(
        private val repository: PodcastRepository,
        private val schedulerProvider: BaseScheduler
) : BaseUseCase<LoadEpisodes.EpisodesRequestValues, LoadEpisodes.EpisodesResultValues>() {

    override fun execute(requestValues: EpisodesRequestValues): EpisodesResultValues {
        return EpisodesResultValues(
                repository.loadEpisodes()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
        )
    }

    class EpisodesRequestValues : RequestValues
    class EpisodesResultValues(val completable: Completable) : ResultValues
}