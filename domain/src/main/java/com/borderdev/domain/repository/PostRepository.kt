package com.borderdev.domain.repository

import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface PostRepository {

    fun loadPosts(): Completable

    fun getPosts(): Observable<List<Post>>

    fun getPostsByType(type: PostType): Flowable<List<Post>>

    fun getPost(postId: Long): Single<Post>

    fun updatePost(post: Post): Completable

    fun clearData(): Completable
}