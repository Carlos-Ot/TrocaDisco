package com.borderdev.domain.usecases.episode

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.TestSchedulerProvider
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test

class GetEpisodesTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository = mock<PodcastRepository>()
    private var schedulerProvider = TestSchedulerProvider()

    private val getEpisodes = GetEpisodes(repository, schedulerProvider)

    private val useCaseParam = mock<GetEpisodes.EpisodesRequestValues>()

    @Test
    fun getEpisodesComplete() {
        stubGetEpisodes(DomainDataFactory.makeEpisodeList(5))

        val testObserver = getEpisodes.execute(useCaseParam).flowable.test()
        testObserver.assertComplete()
    }

    @Test
    fun getEpisodesWithDate() {
        val episodes = DomainDataFactory.makeEpisodeList(5)
        stubGetEpisodes(episodes)

        val testObserver = getEpisodes.execute(useCaseParam).flowable.test()
        testObserver.assertValue {
            episodes.equals(it)
        }
    }

    private fun stubGetEpisodes(episodes: List<Episode>) {
        whenever(repository.getEpisodes()).thenReturn(Flowable.fromArray(episodes))
    }
}