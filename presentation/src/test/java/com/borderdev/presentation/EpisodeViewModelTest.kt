package com.borderdev.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.model.Episode
import com.borderdev.domain.usecases.episode.GetEpisode
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
class EpisodeViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getEpisode = mock<GetEpisode>()
    private val episodeViewModel = EpisodeViewModel(getEpisode)

    @Test
    fun fetchEpisodeExecutesUseCase() {
        whenever(getEpisode.execute(any()))
                .thenReturn(GetEpisode.EpisodeResultValues(
                        Single.just(DomainDataFactory.makeEpisode())
                ))

        episodeViewModel.fetchData()
        verify(getEpisode, times(1))
                .execute(any())
    }

    @Test
    fun fetchEpisodeWithSuccess() {
        whenever(getEpisode.execute(any()))
                .thenReturn(GetEpisode.EpisodeResultValues(
                        Single.just(DomainDataFactory.makeEpisode())
                ))

        episodeViewModel.fetchData()

        assertEquals(ViewState.Status.SUCCESS, episodeViewModel.getState().value?.status)
    }

    @Test
    fun fetchEpisodeWithData() {
        val episode = DomainDataFactory.makeEpisode()

        whenever(getEpisode.execute(any()))
                .thenReturn(GetEpisode.EpisodeResultValues(
                        Single.just(episode)
                ))

        episodeViewModel.fetchData()

        assertEquals(episode, episodeViewModel.getState().value?.data)

    }

    @Test
    fun fetchEpisodeWithError() {
        whenever(getEpisode.execute(any()))
                .thenReturn(
                        GetEpisode.EpisodeResultValues(
                                Single.error<Episode>(RuntimeException())
                        ))
        episodeViewModel.fetchData()

        assertEquals(ViewState.Status.ERROR, episodeViewModel.getState().value?.status)

    }
}