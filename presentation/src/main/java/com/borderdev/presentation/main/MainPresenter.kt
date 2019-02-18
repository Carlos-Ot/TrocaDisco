package com.borderdev.presentation.main

import com.borderdev.domain.usecases.episode.GetEpisodes
import com.borderdev.presentation.common.BasePresenter
import io.reactivex.disposables.Disposable

class MainPresenter(private val useCase: GetEpisodes) : BasePresenter<MainView>(){

    private fun getEpisodes() {
        val useCaseParam = GetEpisodes.EpisodesRequestValues()
        val flowable = useCase.execute(useCaseParam).flowable

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
        getEpisodes()
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

}