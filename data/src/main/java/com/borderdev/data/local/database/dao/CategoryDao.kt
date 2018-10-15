package com.borderdev.data.local.database.dao

import androidx.room.*
import com.borderdev.data.local.database.entity.CategoryEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Query("SELECT * FROM tb_category")
    fun getAll(): Flowable<List<CategoryEntity>>

    @Query("SELECT * FROM tb_category WHERE episode_id = :episodeId")
    fun getByEpisode(episodeId: Long): Flowable<List<CategoryEntity>>

    @Query("SELECT * FROM tb_category WHERE _id = :id")
    fun getById(id: Long): Single<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg categories: CategoryEntity): List<Long>

    @Update
    fun update(category: CategoryEntity)

    @Update
    fun update(vararg categories: CategoryEntity)

    @Query("DELETE FROM tb_category WHERE _id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM tb_category WHERE episode_id = :episodeId")
    fun deleteByEpisode(episodeId: Long)

    @Query("DELETE FROM tb_category")
    fun deleteAll()
}