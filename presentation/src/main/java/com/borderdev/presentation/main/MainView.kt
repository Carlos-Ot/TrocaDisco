package com.borderdev.presentation.main

import com.borderdev.domain.model.Episode
import com.borderdev.presentation.common.BaseView

interface MainView: BaseView {

    fun showContent(episodes: List<Episode>)
}