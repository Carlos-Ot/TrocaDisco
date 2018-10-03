package com.borderdev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.borderdev.data.local.database.entity.Post
import com.borderdev.data.local.database.enums.PostType
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PostDao {

    @Query("SELECT * FROM tb_post")
    fun getAll(): Flowable<List<Post>>

    @Query("SELECT * FROM tb_post WHERE _id = :id")
    fun getById(id: Long): Single<Post>

    @Query("SELECT * FROM tb_post WHERE post_type = :type")
    fun getByType(type: PostType): Flowable<List<Post>>

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