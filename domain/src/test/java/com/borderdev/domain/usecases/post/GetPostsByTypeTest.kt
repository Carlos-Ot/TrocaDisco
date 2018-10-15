package com.borderdev.domain.usecases.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post
import com.borderdev.domain.repository.PostRepository
import com.borderdev.domain.schedulers.TestSchedulerProvider
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetPostsByTypeTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository = mock<PostRepository>()
    private var schedulerProvider = TestSchedulerProvider()

    private val getPosts = GetPostsByType(repository, schedulerProvider)

    private val useCaseParam = GetPostsByType.PostsRequestValues(PostType.DEFAULT)

    @Test
    fun getPostsComplete() {
        stubGetPosts(DomainDataFactory.makePostsList(5))

        val testObserver = getPosts.execute(useCaseParam).flowable.test()
        testObserver.assertComplete()
    }

    @Test
    fun getPostsWithDate() {
        val posts = DomainDataFactory.makePostsList(5)
        stubGetPosts(posts)

        val testObserver = getPosts.execute(useCaseParam).flowable.test()
        testObserver.assertValue {
            posts.equals(it)
        }
    }

    private fun stubGetPosts(posts: List<Post>) {
        whenever(repository.getPostsByType(PostType.DEFAULT)).thenReturn(Flowable.fromArray(posts))
    }
}