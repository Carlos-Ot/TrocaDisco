package com.borderdev.data.source.remote.network.entity

import pl.droidsonroids.jspoon.annotation.Selector

data class MainPage (
        @Selector(".cb-grid-block.cb-module-block.cb-s-5.clearfix > div > ul > li > ul > li")
        var highLights: List<Article> = emptyList(),

        @Selector("#cb-section-a > div:nth-child(6) > div.cb-grid-x.cb-grid-5.clearfix > div")
        var articles: List<Article> = emptyList()
)