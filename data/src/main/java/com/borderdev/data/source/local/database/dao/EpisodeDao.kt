package com.borderdev.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.borderdev.data.entity.Episode
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM tb_episode")
    fun getAll(): Flowable<List<Episode>>

    @Query("SELECT * FROM tb_episode WHERE episode_type = :type")
    fun getByType(type: Int): Flowable<List<Episode>>

    @Query("SELECT * FROM tb_episode WHERE _id = :id")
    fun getById(id: Long): Maybe<Episode>

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