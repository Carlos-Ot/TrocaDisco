package com.borderdev.trocaodisco.ui.activity

import android.util.Log
import com.borderdev.domain.model.Episode
import com.borderdev.presentation.main.MainPresenter
import com.borderdev.presentation.main.MainView
import com.borderdev.trocaodisco.R
import com.borderdev.trocaodisco.ui.common.BaseActivity
import org.kodein.di.Kodein
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class MainActivity() : BaseActivity<MainView>(), MainView {

    override val kodein: Kodein by closestKodein()

    override val layoutId: Int = R.layout.activity_main
    override val presenter: MainPresenter by instance()

    override fun setPresenter() {
        presenter.attachView(this)
    }

    override fun onCreate() {
        presenter.subscribe()
    }

    override fun showError(error: Throwable) {
        Log.e("XABLAU", "Error: ${error.message}")
    }

    override fun showContent(episodes: List<Episode>) {
        val episode = episodes.first()

        Log.d("XABLAU", "Episode: $episode")

    }


}