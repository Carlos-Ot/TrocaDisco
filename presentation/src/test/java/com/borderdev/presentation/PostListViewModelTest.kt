package com.borderdev.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.model.Post
import com.borderdev.domain.usecases.post.GetPostsByType
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.RuntimeException
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PostListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getPosts = mock<GetPostsByType>()
    private val postListViewModel = PostListViewModel(getPosts)

    @Test
    fun fetchPostsExecutesUseCase() {
        whenever(getPosts.execute(any()))
                .thenReturn(GetPostsByType.PostsResultValues(
                        Flowable.fromArray(DomainDataFactory.makePostsList(5))
                ))
        postListViewModel.fetchData()
        verify(getPosts, times(1)).execute(any())

    }

    @Test
    fun fetchPostsWithSuccess() {
        whenever(getPosts.execute(any()))
                .thenReturn(GetPostsByType.PostsResultValues(
                        Flowable.fromArray(DomainDataFactory.makePostsList(5))
                ))

        postListViewModel.fetchData()

        assertEquals(ViewState.Status.SUCCESS, postListViewModel.getState().value?.status)

    }

    @Test
    fun fetchPostsWithData() {
        val posts = DomainDataFactory.makePostsList(5)

        whenever(getPosts.execute(any()))
                .thenReturn(GetPostsByType.PostsResultValues(
                        Flowable.fromArray(posts)
                ))

        postListViewModel.fetchData()

        assertEquals(posts, postListViewModel.getState().value?.data)

    }

    @Test
    fun fetchPostsWithError() {
        whenever(getPosts.execute(any()))
                .thenReturn(GetPostsByType.PostsResultValues(
                        Flowable.error<List<Post>>(RuntimeException())
                ))

        postListViewModel.fetchData()

        assertEquals(ViewState.Status.ERROR, postListViewModel.getState().value?.status)
    }
}