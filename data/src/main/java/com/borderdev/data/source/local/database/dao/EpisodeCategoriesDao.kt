package com.borderdev.data.source.local.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.borderdev.data.entity.EpisodeCategories

@Dao
interface EpisodeCategoriesDao {

    @Transaction
    @Query("SELECT * FROM tb_episode")
    fun getEpisodesWithCategories(): List<EpisodeCategories>
}