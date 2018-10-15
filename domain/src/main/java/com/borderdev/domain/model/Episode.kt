package com.borderdev.domain.model

import com.borderdev.domain.enums.EpisodeType

data class Episode (
        val id: Long = 0,

        val title: String = "",

        val episodeNumber: Int = 0,

        val description: String = "",

        val content: String = "",

        val pubDate: String = "",

        var downloadUrl: String = "",

        var downloaded: Boolean = false,

        var downloadPath: String = "",

        val episodeType: EpisodeType = EpisodeType.DEFAULT,

        var categories: List<Category> = emptyList()
)