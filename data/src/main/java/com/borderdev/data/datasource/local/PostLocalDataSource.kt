package com.borderdev.data.datasource.local

import com.borderdev.data.local.database.entity.Post
import com.borderdev.data.local.database.enums.PostType
import io.reactivex.Flowable
import io.reactivex.Single

interface PostLocalDataSource {

    fun getPosts(): Flowable<List<Post>>

    fun getPost(postId: Long): Single<Post>

    fun getPostsByType(type: PostType): Flowable<List<Post>>

    fun savePost(post: Post)

    fun removePost(postId: Long)

    fun updatePost(post: Post)

    fun clearData()
}