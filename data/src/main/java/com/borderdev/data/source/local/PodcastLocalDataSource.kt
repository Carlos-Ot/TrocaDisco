package com.borderdev.data.source.local

import com.borderdev.data.source.datasource.local.PodcastLocalDataSource
import com.borderdev.data.source.local.database.EpiType
import com.borderdev.data.source.local.database.dao.EpisodeDao
import com.borderdev.data.source.local.database.entity.Episode
import com.borderdev.data.source.local.database.entity.enum.EpisodeType
import io.reactivex.Flowable
import io.reactivex.Single

class PodcastLocalDataSource(private val episodeDao: EpisodeDao) : PodcastLocalDataSource {

    override fun getEpisodes(): Flowable<List<Episode>> {
        return episodeDao.getAll()
    }

    override fun getEpisodesByType(type: EpiType): Flowable<List<Episode>> {
        return episodeDao.getByType(type)
    }

    override fun getEpisode(episodeId: Long): Single<Episode> {
        return episodeDao.getById(episodeId)
    }

    override fun saveEpisode(episode: Episode) {
        episodeDao.insert(episode)
    }

    override fun removeEpisode(episodeId: Long) {
        episodeDao.deleteById(episodeId)
    }

    override fun updateEpisode(episode: Episode) {
        episodeDao.update(episode)
    }

    override fun clearData() {
        episodeDao.deleteAll()
    }
}