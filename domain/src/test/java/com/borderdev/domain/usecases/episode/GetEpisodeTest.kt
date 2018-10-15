package com.borderdev.domain.usecases.episode

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
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
class GetEpisodeTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository = mock<PodcastRepository>()
    private var schedulerProvider = TestSchedulerProvider()

    private val getEpisode = GetEpisode(repository, schedulerProvider)

    private val useCaseParam = mock<GetEpisode.EpisodeRequestValues>()

    @Test
    fun getPostComplete() {

        stubGetEpisode(DomainDataFactory.makeEpisode())

        val testObserver = getEpisode.execute(useCaseParam).single.test()
        testObserver.assertComplete()
    }

    @Test
    fun getPostWithData() {
        val episode = DomainDataFactory.makeEpisode()

        stubGetEpisode(episode)

        val testObserver = getEpisode.execute(useCaseParam).single.test()
        testObserver.assertValue { result ->
            episode.equals(result)
        }
    }

    private fun stubGetEpisode(episode: Episode) {
        whenever(repository.getEpisode(any())).thenReturn(Single.just(episode))
    }
}