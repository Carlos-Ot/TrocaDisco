package com.borderdev.data.source.local.database.dao

import android.arch.persistence.room.*
import com.borderdev.data.entity.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM tb_category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM tb_category WHERE episode_id = :episodeId")
    fun getByEpisode(episodeId: Long): List<Category>

    @Query("SELECT * FROM tb_category WHERE _id = :id")
    fun getById(id: Long): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg categories: Category): List<Long>

    @Update
    fun update(category: Category)

    @Update
    fun update(vararg categories: Category)

    @Query("DELETE FROM tb_category WHERE _id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM tb_category")
    fun deleteAll()
}