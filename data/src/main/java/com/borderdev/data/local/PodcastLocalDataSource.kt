package com.borderdev.data.local

import com.borderdev.data.local.database.AppDatabase
import com.borderdev.data.local.database.dao.CategoryDao
import com.borderdev.domain.datasource.PodcastLocalDataSource
import com.borderdev.data.local.database.dao.EpisodeCategoriesDao
import com.borderdev.data.local.database.dao.EpisodeDao
import com.borderdev.data.local.database.entity.CategoryEntity
import com.borderdev.data.local.database.entity.EpisodeCategories
import com.borderdev.data.local.mapper.CategoryMapper
import com.borderdev.data.local.mapper.EpisodeCategoriesMapper
import com.borderdev.data.local.mapper.EpisodeMapper
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PodcastLocalDataSource(private val appDatabase: AppDatabase) : PodcastLocalDataSource {

    private val episodeDao: EpisodeDao = appDatabase.episodeDao()
    private val episodeCategoriesDao: EpisodeCategoriesDao = appDatabase.episodeCategoriesDao()
    private val categoryDao: CategoryDao = appDatabase.categoryDao()

    override fun getEpisodes(): Flowable<List<Episode>> {
        return episodeCategoriesDao.getEpisodesCategories()
                .map { episodeCategories ->
                    episodeCategories.map { EpisodeCategoriesMapper.toDomain(it) }
                }
    }

    override fun getEpisodesByType(type: EpisodeType): Flowable<List<Episode>> {
        return episodeCategoriesDao.getEpisodesCategoriesByType(type)
                .map { episodeCategories ->
                    episodeCategories.map { EpisodeCategoriesMapper.toDomain(it) }
                }
    }

    override fun getEpisode(episodeId: Long): Single<Episode> {
        return episodeCategoriesDao.getEpisodeCategoryById(episodeId)
                .map { EpisodeCategoriesMapper.toDomain(it) }
    }

    override fun saveEpisode(episode: Episode): Completable {
        return Completable.defer {
            episodeCategoriesDao.insertEpisodeWithCategories(
                    EpisodeMapper.fromDomain(episode),
                    episode.categories.map { CategoryMapper.fromDomain(it) },
                    episodeDao,
                    categoryDao
                    )
            Completable.complete()
        }
    }

    override fun removeEpisode(episodeId: Long): Completable {
        return Completable.defer {
            episodeCategoriesDao.deleteEpisodeWithCategories(
                    episodeId,
                    episodeDao,
                    categoryDao
            )
            Completable.complete()
        }
    }

    override fun updateEpisode(episode: Episode): Completable {
        return Completable.defer {
            episodeDao.update(EpisodeMapper.fromDomain(episode))
            Completable.complete()
        }
    }

    override fun clearData(): Completable {
        return Completable.defer {
            episodeDao.deleteAll()
            categoryDao.deleteAll()
            Completable.complete()
        }
    }
}