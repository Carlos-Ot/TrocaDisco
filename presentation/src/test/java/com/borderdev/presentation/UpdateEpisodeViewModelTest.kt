package com.borderdev.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.borderdev.domain.usecases.episode.UpdateEpisode
import com.borderdev.presentation.data_factory.DomainDataFactory
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Completable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.RuntimeException
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class UpdateEpisodeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var updateEpisode = mock<UpdateEpisode>()
    private val updateEpisodeViewModel = UpdateEpisodeViewModel(updateEpisode)

    private val episode = DomainDataFactory.makeEpisode()

    @Test
    fun updateEpisodeExecutesUseCase() {
        whenever(updateEpisode.execute(any()))
                .thenReturn(UpdateEpisode.UpdateResultValues(
                        Completable.complete()
                ))

        updateEpisodeViewModel.updateEpisode(episode)
        verify(updateEpisode, times(1)).execute(any())
    }

    @Test
    fun updateEpisodeWithSuccess() {
        whenever(updateEpisode.execute(any()))
                .thenReturn(UpdateEpisode.UpdateResultValues(
                        Completable.complete()
                ))

        updateEpisodeViewModel.updateEpisode(episode)

        assertEquals(ViewState.Status.SUCCESS, updateEpisodeViewModel.getState().value?.status)
    }

    @Test
    fun updateEpisodeWithError() {
        whenever(updateEpisode.execute(any()))
                .thenReturn(UpdateEpisode.UpdateResultValues(
                        Completable.error(RuntimeException())
                ))

        updateEpisodeViewModel.updateEpisode(episode)

        assertEquals(ViewState.Status.ERROR, updateEpisodeViewModel.getState().value?.status)
    }
}