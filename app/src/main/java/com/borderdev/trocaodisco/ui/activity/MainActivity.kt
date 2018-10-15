package com.borderdev.trocaodisco.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import com.borderdev.presentation.BaseViewModel
import com.borderdev.presentation.EpisodeListViewModel
import com.borderdev.trocaodisco.R
import com.borderdev.trocaodisco.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity() : BaseActivity<List<Episode>>() {

    override val kodein: Kodein by closestKodein()

    override val layoutId: Int = R.layout.activity_main

    override val viewModel: EpisodeListViewModel by instance()

    override fun onCreate() {
    }

    override fun observe() {
        viewModel.episodeType = EpisodeType.PODCAST
        viewModel.getState().observe(this, Observer { newState ->
            newState?.let {
                handleState(it)
            }
        })
    }

    override fun progressIndicator(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun handleSuccess(data: List<Episode>) {
        progressIndicator(false)
        data.forEach {
            Log.d("XABLAU", "Episode: ${it.title}")
        }
    }

    override fun handleError(error: Throwable) {
        progressIndicator(false)
        Log.e("XABLAU", "Error: ${error.message}")
    }

}