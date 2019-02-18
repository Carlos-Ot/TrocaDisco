package com.borderdev.presentation.splash

import com.borderdev.domain.usecases.episode.LoadEpisodes
import com.borderdev.presentation.common.BasePresenter
import io.reactivex.disposables.Disposable

class SplashPresenter(private val useCase: LoadEpisodes): BasePresenter<SplashView>() {

    private fun loadEpisodes() {
        val useCaseParam = LoadEpisodes.EpisodesRequestValues()
        val completable = useCase.execute(useCaseParam).completable

        val disposable: Disposable = completable.subscribe(
                {
                    view?.showMainContent()
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