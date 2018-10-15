package com.borderdev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.borderdev.data.local.database.entity.CategoryEntity
import com.borderdev.data.local.database.entity.EpisodeCategories
import com.borderdev.data.local.database.entity.EpisodeEntity
import com.borderdev.domain.enums.EpisodeType
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface EpisodeCategoriesDao {

    @Transaction
    @Query("SELECT * FROM tb_episode")
    fun getEpisodesCategories(): Flowable<List<EpisodeCategories>>

    @Transaction
    @Query("SELECT * FROM tb_episode WHERE episode_number = :episodeType")
    fun getEpisodesCategoriesByType(episodeType: EpisodeType): Flowable<List<EpisodeCategories>>

    @Transaction
    @Query("SELECT * FROM tb_episode WHERE _id = :episodeId")
    fun getEpisodeCategoryById(episodeId: Long): Single<EpisodeCategories>

    @Transaction
    fun insertEpisodeWithCategories(episodeEntity: EpisodeEntity,
                                     categoryEntities: List<CategoryEntity>,
                                     episodeDao: EpisodeDao,
                                     categoryDao: CategoryDao) {

        val episodeId = episodeDao.insert(episodeEntity)

        categoryEntities.forEach {
            categoryDao.insert(it.apply { it.episodeId = episodeId })
        }

    }

    @Transaction
    fun deleteEpisodeWithCategories(episodeId: Long,
                                    episodeDao: EpisodeDao,
                                    categoryDao: CategoryDao) {
        episodeDao.deleteById(episodeId)
        categoryDao.deleteByEpisode(episodeId)
    }
}