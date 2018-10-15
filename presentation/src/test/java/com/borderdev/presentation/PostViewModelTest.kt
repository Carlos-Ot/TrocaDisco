package com.borderdev.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.model.Post
import com.borderdev.domain.usecases.post.GetPost
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.RuntimeException
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PostViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getPost = mock<GetPost>()
    private val postViewModel = PostViewModel(getPost)

    @Test
    fun fetchPostExecutesUseCase() {
        whenever(getPost.execute(any()))
                .thenReturn(GetPost.PostResultValues(
                        Single.just(DomainDataFactory.makePost())
                ))

        postViewModel.fetchData()
        verify(getPost, times(1))
                .execute(any())
    }

    @Test
    fun fetchPostWithSuccess() {
        whenever(getPost.execute(any()))
                .thenReturn(GetPost.PostResultValues(
                        Single.just(DomainDataFactory.makePost())
                ))

        postViewModel.fetchData()

        assertEquals(ViewState.Status.SUCCESS, postViewModel.getState().value?.status)
    }

    @Test
    fun fetchPostWithData() {
        val post = DomainDataFactory.makePost()

        whenever(getPost.execute(any()))
                .thenReturn(GetPost.PostResultValues(
                        Single.just(post)
                ))

        this.postViewModel.fetchData()

        assertEquals(post, this.postViewModel.getState().value?.data)

    }

    @Test
    fun fetchPostWithError() {
        whenever(getPost.execute(any()))
                .thenReturn(
                        GetPost.PostResultValues(
                                Single.error<Post>(RuntimeException())
                        ))
        postViewModel.fetchData()

        assertEquals(ViewState.Status.ERROR, postViewModel.getState().value?.status)

    }
}