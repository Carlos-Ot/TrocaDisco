package com.borderdev.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import com.borderdev.domain.usecases.episode.GetEpisodeByType
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import java.lang.RuntimeException
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class EpisodeListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getEpisodes = mock<GetEpisodeByType>()
    private var episodeListViewModel = EpisodeListViewModel(getEpisodes)

    @Test
    fun fetchEpisodesExecutesUseCase() {

        whenever(getEpisodes.execute(any()))
                .thenReturn(GetEpisodeByType.EpisodesResultValues(
                        Flowable.fromArray(DomainDataFactory.makeEpisodeList(5)))
                )

        episodeListViewModel.fetchData()
        verify(getEpisodes, times(1))
                .execute(any())
    }

    @Test
    fun fetchEpisodesWithSuccess() {

        whenever(getEpisodes.execute(any()))
                .thenReturn(GetEpisodeByType.EpisodesResultValues(
                        Flowable.fromArray(DomainDataFactory.makeEpisodeList(5)))
                )
        episodeListViewModel.fetchData()

        assertEquals(ViewState.Status.SUCCESS, episodeListViewModel.getState().value?.status)
    }

    @Test
    fun fetchEpisodesWithData() {
        val episodes = DomainDataFactory.makeEpisodeList(5)

        whenever(getEpisodes.execute(any()))
                .thenReturn(
                        GetEpisodeByType.EpisodesResultValues(
                                Flowable.fromArray(episodes))
                )
        episodeListViewModel.fetchData()

        assertEquals(episodes, episodeListViewModel.getState().value?.data)
    }

    @Test
    fun fetchEpisodesWithError() {

        whenever(getEpisodes.execute(any()))
                .thenReturn(
                        GetEpisodeByType.EpisodesResultValues(
                                Flowable.error<List<Episode>>(RuntimeException())
                        )
                )

        episodeListViewModel.fetchData()

        assertEquals(ViewState.Status.ERROR, episodeListViewModel.getState().value?.status)
    }

}