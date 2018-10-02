package com.borderdev.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.borderdev.data.source.local.database.entity.EpisodeCategories
import io.reactivex.Flowable

@Dao
interface EpisodeCategoriesDao {

    @Transaction
    @Query("SELECT * FROM tb_episode")
    fun getEpisodesWithCategories(): Flowable<List<EpisodeCategories>>
}