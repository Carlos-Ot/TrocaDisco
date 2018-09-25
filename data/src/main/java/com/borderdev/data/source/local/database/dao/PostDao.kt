package com.borderdev.data.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.borderdev.data.entity.Post
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface PostDao {

    @Query("SELECT * FROM tb_post")
    fun getAll(): Flowable<List<Post>>

    @Query("SELECT * FROM tb_post WHERE _id = :id")
    fun getById(id: Long): Maybe<Post>

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