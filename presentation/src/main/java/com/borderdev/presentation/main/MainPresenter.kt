package com.borderdev.presentation.main

import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.usecases.episode.GetEpisodeByType
import com.borderdev.domain.usecases.episode.GetEpisodes
import com.borderdev.presentation.common.BasePresenter
import io.reactivex.disposables.Disposable

class MainPresenter(private val useCase: GetEpisodes) : BasePresenter<MainView>(){

    fun loadEpisodes() {
        val useCaseParam = GetEpisodes.EpisodesRequestValues()
        val flowable = useCase.execute(useCaseParam).observable

        val disposable: Disposable = flowable.subscribe(
                {
                    view?.showContent(it)
                },
                {
                    view?.showError(it)
                }
        )

        compositeDisposable.add(disposable)
    }

    override fun subscribe() {
        loadEpisodes()
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

}