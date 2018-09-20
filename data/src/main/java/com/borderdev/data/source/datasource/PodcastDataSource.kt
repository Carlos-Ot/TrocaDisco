package com.borderdev.data.source.datasource

import com.borderdev.data.entity.Episode
import com.borderdev.data.entity.enum.EpisodeType

interface PodcastDataSource {

    fun getEpisodes(): List<Episode>

    fun getEpisodesByType(type: EpisodeType): List<Episode>

    fun getEpisode(episodeId: Int): Episode

    fun saveEpisodes(vararg episode: Episode)

    fun removeEpisode(episodeId: Int)

    fun updateEpisode(episode: Episode)

    fun clearData()
}