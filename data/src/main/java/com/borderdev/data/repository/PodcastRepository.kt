package com.borderdev.data.repository

import com.borderdev.data.entity.Episode
import com.borderdev.data.entity.enum.EpisodeType
import com.borderdev.data.source.datasource.PodcastDataSource

class PodcastRepository : PodcastDataSource {

    override fun getEpisodes(): List<Episode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEpisodesByType(type: EpisodeType): List<Episode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEpisode(episodeId: Int): Episode {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveEpisodes(vararg episode: Episode) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeEpisode(episodeId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateEpisode(episode: Episode) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}