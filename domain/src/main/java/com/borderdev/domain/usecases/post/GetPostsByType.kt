package com.borderdev.domain.usecases.post

import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post
import com.borderdev.domain.repository.PostRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Flowable

class GetPostsByType(
        private val repository: PostRepository,
        private val schedulerProvider: BaseScheduler
): BaseUseCase<GetPostsByType.PostsRequestValues, GetPostsByType.PostsResultValues>() {

    override fun execute(requestValues: PostsRequestValues): PostsResultValues {
        return PostsResultValues(
                repository.getPostsByType(requestValues.postType)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
        )
    }

    class PostsRequestValues(val postType: PostType) : RequestValues
    class PostsResultValues(val flowable: Flowable<List<Post>>) : ResultValues
}