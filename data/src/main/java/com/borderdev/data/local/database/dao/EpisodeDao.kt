package com.borderdev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.borderdev.data.local.database.entity.EpisodeEntity
import com.borderdev.domain.enums.EpisodeType
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM tb_episode")
    fun getAll(): Flowable<List<EpisodeEntity>>

    @Query("SELECT * FROM tb_episode WHERE episode_type = :type")
    fun getByType(type: EpisodeType): Flowable<List<EpisodeEntity>>

    @Query("SELECT * FROM tb_episode WHERE _id = :id")
    fun getById(id: Long): Single<EpisodeEntity>

    @Insert(onConflict = IGNORE)
    fun insert(episode: EpisodeEntity): Long

    @Insert(onConflict = IGNORE)
    fun insert(vararg episodes: EpisodeEntity): List<Long>

    @Update
    fun update(episode: EpisodeEntity)

    @Update
    fun update(vararg episodes: EpisodeEntity)

    @Query("DELETE FROM tb_episode WHERE _id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM tb_episode")
    fun deleteAll()
}