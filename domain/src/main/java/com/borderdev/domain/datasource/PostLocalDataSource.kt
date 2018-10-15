package com.borderdev.domain.datasource

import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface PostLocalDataSource {

    fun getPosts(): Observable<List<Post>>

    fun getPost(postId: Long): Single<Post>

    fun getPostsByType(type: PostType): Flowable<List<Post>>

    fun savePost(post: Post): Completable

    fun removePost(postId: Long): Completable

    fun updatePost(post: Post): Completable

    fun clearData(): Completable
}