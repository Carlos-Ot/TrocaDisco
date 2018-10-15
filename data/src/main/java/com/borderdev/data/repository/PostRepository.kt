package com.borderdev.data.repository

import com.borderdev.domain.datasource.PostLocalDataSource
import com.borderdev.domain.datasource.PostRemoteDataSource
import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post
import com.borderdev.domain.repository.PostRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class PostRepository(
        private val remoteDataSource: PostRemoteDataSource,
        private val localDataSource: PostLocalDataSource
) : PostRepository {

    override fun getPosts(): Observable<List<Post>> {
        return remoteDataSource.getPosts()
                .publish { remote ->
                    remote.flatMap { posts ->
                        posts.forEach {
                            localDataSource.savePost(it)
                        }
                        Observable.just(posts)
                    }
                    Observable.merge(remote, localDataSource.getPosts().takeUntil(remote))
                }
    }

    override fun getPostsByType(type: PostType): Flowable<List<Post>> {
        return localDataSource.getPostsByType(type)
    }

    override fun getPost(postId: Long): Single<Post> {
        return localDataSource.getPost(postId)
    }

    override fun updatePost(post: Post): Completable {
        return localDataSource.updatePost(post)
    }

    override fun clearData(): Completable {
        return localDataSource.clearData()
    }
}