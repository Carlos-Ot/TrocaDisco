package com.borderdev.domain.usecases.episode

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.schedulers.TestSchedulerProvider
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UpdateEpisodeTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository = mock<PodcastRepository>()
    private var schedulerProvider = TestSchedulerProvider()

    private val updateEpisode = UpdateEpisode(repository, schedulerProvider)

    private val useCaseParam = UpdateEpisode.UpdateRequestValues(DomainDataFactory.makeEpisode())

    @Test
    fun updateEpisodeComplete() {
        stubUpdateEpisode()

        val testObserver = updateEpisode.execute(useCaseParam).completable.test()
        testObserver.assertComplete()
    }

    private fun stubUpdateEpisode() {
        whenever(repository.updateEpisode(any())).thenReturn(Completable.complete())
    }
}