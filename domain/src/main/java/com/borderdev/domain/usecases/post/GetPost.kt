package com.borderdev.domain.usecases.post

import com.borderdev.domain.model.Post
import com.borderdev.domain.repository.PostRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Single

class GetPost(
        private val repository: PostRepository,
        private val schedulerProvider: BaseScheduler
): BaseUseCase<GetPost.PostRequestValues, GetPost.PostResultValues>() {

    override fun execute(requestValues: PostRequestValues): PostResultValues {
        return PostResultValues(
                repository.getPost(requestValues.episodeId)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
        )
    }

    class PostRequestValues(val episodeId: Long): RequestValues
    class PostResultValues(val single: Single<Post>): ResultValues
}