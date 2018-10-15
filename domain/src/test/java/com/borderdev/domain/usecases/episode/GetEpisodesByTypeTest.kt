package com.borderdev.domain.usecases.episode

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.TestSchedulerProvider
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetEpisodesByTypeTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository = mock<PodcastRepository>()
    private var schedulerProvider = TestSchedulerProvider()

    private val getEpisodes = GetEpisodeByType(repository, schedulerProvider)

    private val useCaseParam = GetEpisodeByType.EpisodesRequestValues(EpisodeType.DEFAULT)

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
        whenever(repository.getEpisodesByType(EpisodeType.DEFAULT)).thenReturn(Flowable.fromArray(episodes))
    }
}