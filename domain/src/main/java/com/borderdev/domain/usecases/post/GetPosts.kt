package com.borderdev.domain.usecases.post

import com.borderdev.domain.model.Post
import com.borderdev.domain.repository.PostRepository
import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.usecases.BaseUseCase
import io.reactivex.Observable

class GetPosts(
        private val repository: PostRepository,
        private val schedulerProvider: BaseScheduler
): BaseUseCase<GetPosts.PostsRequestValues, GetPosts.PostsResultValues>() {

    override fun execute(requestValues: PostsRequestValues): PostsResultValues {
        return PostsResultValues(
                repository.getPosts()
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
        )
    }

    class PostsRequestValues(): RequestValues
    class PostsResultValues(val observable: Observable<List<Post>>): ResultValues
}