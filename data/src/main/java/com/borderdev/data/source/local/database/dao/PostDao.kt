package com.borderdev.data.source.local.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.borderdev.data.entity.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM tb_post")
    fun getAll(): List<Post>

    @Query("SELECT * FROM tb_post WHERE _id = :id")
    fun getById(id: Long): Post

    @Insert(onConflict = REPLACE)
    fun insert(episode: Post): Long

    @Insert(onConflict = REPLACE)
    fun insert(vararg episodes: Post): List<Long>

    @Update
    fun update(post: Post)

    @Update
    fun update(vararg posts: Post)

    @Query("DELETE FROM tb_post WHERE _id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM tb_post")
    fun deleteAll()

}