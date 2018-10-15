package com.borderdev.presentation

import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post
import com.borderdev.domain.usecases.post.GetPostsByType
import io.reactivex.Flowable

class PostListViewModel(private val getPostsByType: GetPostsByType): BaseViewModel<List<Post>>() {

    var postType: PostType = PostType.DEFAULT

    override fun fetchData() {
        state.postValue(ViewState(ViewState.Status.LOADING))

        val useCaseParam = GetPostsByType.PostsRequestValues(postType)
        val flowable: Flowable<List<Post>> = getPostsByType.execute(useCaseParam).flowable

        disposables.add(
                flowable.subscribe(
                        { posts ->
                           state.postValue(ViewState(status = ViewState.Status.SUCCESS, data = posts))
                        },
                        { error ->
                            state.postValue(ViewState(status = ViewState.Status.ERROR, error = error))
                        }
                )
        )
    }
}