package com.borderdev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.borderdev.data.local.database.entity.PostEntity
import com.borderdev.domain.enums.PostType
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface PostDao {

    @Query("SELECT * FROM tb_post")
    fun getAll(): Observable<List<PostEntity>>

    @Query("SELECT * FROM tb_post WHERE _id = :id")
    fun getById(id: Long): Single<PostEntity>

    @Query("SELECT * FROM tb_post WHERE post_type = :type")
    fun getByType(type: PostType): Flowable<List<PostEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(episode: PostEntity): Long

    @Insert(onConflict = REPLACE)
    fun insert(vararg episodes: PostEntity): List<Long>

    @Update
    fun update(post: PostEntity)

    @Update
    fun update(vararg posts: PostEntity)

    @Query("DELETE FROM tb_post WHERE _id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM tb_post")
    fun deleteAll()

}