package com.borderdev.data.source.datasource.local

import com.borderdev.data.source.local.database.EpiType
import com.borderdev.data.source.local.database.entity.Post
import com.borderdev.data.source.local.database.entity.enum.PostType
import io.reactivex.Flowable
import io.reactivex.Single

interface PostLocalDataSource {

    fun getPosts(): Flowable<List<Post>>

    fun getPost(postId: Long): Single<Post>

    fun getPostsByType(type: EpiType): Flowable<List<Post>>

    fun savePost(post: Post)

    fun removePost(postId: Long)

    fun updatePost(post: Post)

    fun clearData()
}