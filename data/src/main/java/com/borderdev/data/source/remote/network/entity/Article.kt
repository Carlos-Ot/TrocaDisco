package com.borderdev.data.source.remote.network.entity

import pl.droidsonroids.jspoon.annotation.Selector

data class Article (
        @Selector(value = "div.cb-grid-img > a > img", attr = "src")
        var imgUrl: String = "",

        @Selector(value = "div.cb-article-meta > h2 > a")
        var title: String = "",

        @Selector(value = "div.cb-article-meta > h2 > a", attr = "href")
        var postUrl: String = "",

        @Selector(value = "div.cb-article-meta > div > span.cb-date > time")
        var pubDate: String = ""
)