package com.borderdev.data.local

import com.borderdev.data.datasource.local.PodcastLocalDataSource
import com.borderdev.data.local.database.dao.EpisodeCategoriesDao
import com.borderdev.data.local.database.dao.EpisodeDao
import com.borderdev.data.local.database.entity.Episode
import com.borderdev.data.local.database.entity.EpisodeCategories
import com.borderdev.data.local.database.enums.EpisodeType
import io.reactivex.Flowable
import io.reactivex.Single

class PodcastLocalDataSource(private val episodeDao: EpisodeDao, private val episodeCategoriesDao: EpisodeCategoriesDao) : PodcastLocalDataSource {

    override fun getEpisodes(): Flowable<List<Episode>> {
        return episodeDao.getAll()
    }

    override fun getEpisodesCategories(): Flowable<List<EpisodeCategories>> {
        return episodeCategoriesDao.getEpisodesCategories()
    }

    override fun getEpisodesCategoriesByTipe(episodeType: EpisodeType): Flowable<List<EpisodeCategories>> {
        return episodeCategoriesDao.getEpisodesCategoriesByType(episodeType)
    }

    override fun getEpisodeCategoryById(episodeId: Long): EpisodeCategories {
        return episodeCategoriesDao.getEpisodeCategoryById(episodeId)
    }

    override fun getEpisodesByType(type: EpisodeType): Flowable<List<Episode>> {
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