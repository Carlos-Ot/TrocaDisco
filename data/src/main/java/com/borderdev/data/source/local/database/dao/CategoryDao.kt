package com.borderdev.data.source.local.database.dao

import com.borderdev.data.entity.Category
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface CategoryDao {
    @Query("SELECT * FROM tb_category")
    fun getAll(): Flowable<List<Category>>

    @Query("SELECT * FROM tb_category WHERE episode_id = :episodeId")
    fun getByEpisode(episodeId: Long): Flowable<List<Category>>

    @Query("SELECT * FROM tb_category WHERE _id = :id")
    fun getById(id: Long): Maybe<Category>

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