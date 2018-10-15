package com.borderdev.domain.usecases.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.model.Post
import com.borderdev.domain.repository.PostRepository
import com.borderdev.domain.schedulers.TestSchedulerProvider
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetPostTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository = mock<PostRepository>()
    private var schedulerProvider = TestSchedulerProvider()

    private val getPost = GetPost(repository, schedulerProvider)

    private val useCaseParam = mock<GetPost.PostRequestValues>()

    @Test
    fun getPostComplete() {

        stubGetPost(DomainDataFactory.makePost())

        val testObserver = getPost.execute(useCaseParam).single.test()
        testObserver.assertComplete()
    }

    @Test
    fun getPostWithData() {
        val post = DomainDataFactory.makePost()

        stubGetPost(post)

        val testObserver = getPost.execute(useCaseParam).single.test()
        testObserver.assertValue { result ->
            post.equals(result)
        }
    }

    private fun stubGetPost(post: Post) {
        whenever(repository.getPost(any())).thenReturn(Single.just(post))
    }
}