package com.borderdev.presentation

import com.borderdev.domain.model.Post
import com.borderdev.domain.usecases.post.GetPost
import io.reactivex.Single
import kotlin.math.sin

class PostViewModel(private val getPost: GetPost): BaseViewModel<Post>() {

    var postId: Long = 0

    override fun fetchData() {
        state.postValue(ViewState(status = ViewState.Status.LOADING))

        val useCaseParam = GetPost.PostRequestValues(postId)
        val single: Single<Post> = getPost.execute(useCaseParam).single

        disposables.add(
                single.subscribe(
                        { post ->
                            state.postValue(ViewState(status = ViewState.Status.SUCCESS, data = post))
                        },
                        { error ->
                            state.postValue(ViewState(status = ViewState.Status.ERROR, error = error))
                        }
                )
        )
    }
}