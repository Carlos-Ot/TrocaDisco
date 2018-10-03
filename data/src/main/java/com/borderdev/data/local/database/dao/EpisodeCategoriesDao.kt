package com.borderdev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.borderdev.data.local.database.entity.Episode
import com.borderdev.data.local.database.entity.EpisodeCategories
import com.borderdev.data.local.database.enums.EpisodeType
import io.reactivex.Flowable

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
    fun getEpisodeCategoryById(episodeId: Long): EpisodeCategories
}