package com.borderdev.domain.usecases.episode

import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Completable

class UpdateEpisode(
        private val repository: PodcastRepository,
        private val schedulerProvider: BaseScheduler
): BaseUseCase<UpdateEpisode.UpdateRequestValues, UpdateEpisode.UpdateResultValues>() {

    override fun execute(requestValues: UpdateRequestValues): UpdateResultValues {
        return UpdateResultValues(
                repository.updateEpisode(requestValues.episode)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
        )
    }

    class UpdateRequestValues(val episode: Episode): RequestValues
    class UpdateResultValues(val completable: Completable):ResultValues
}