package com.borderdev.data.source.local.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.IGNORE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.borderdev.data.entity.Episode

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM tb_episode")
    fun getAll(): List<Episode>

    @Query("SELECT * FROM tb_episode WHERE episode_type = :type")
    fun getByType(type: Int): List<Episode>

    @Query("SELECT * FROM tb_episode WHERE _id = :id")
    fun getById(id: Long): Episode

    @Insert(onConflict = IGNORE)
    fun insert(episode: Episode): Long

    @Insert(onConflict = IGNORE)
    fun insert(vararg episodes: Episode): List<Long>

    @Update
    fun update(episode: Episode)

    @Update
    fun update(vararg episodes: Episode)

    @Query("DELETE FROM tb_episode WHERE _id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM tb_episode")
    fun deleteAll()
}