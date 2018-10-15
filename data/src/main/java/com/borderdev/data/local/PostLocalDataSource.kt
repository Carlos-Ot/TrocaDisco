package com.borderdev.data.local

import com.borderdev.data.local.database.AppDatabase
import com.borderdev.domain.datasource.PostLocalDataSource
import com.borderdev.data.local.database.dao.PostDao
import com.borderdev.data.local.mapper.PostMapper
import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class PostLocalDataSource(private val appDatabase: AppDatabase) : PostLocalDataSource {

    private val postDao: PostDao = appDatabase.postDao()

    override fun getPosts(): Observable<List<Post>> {
        return postDao.getAll()
                .map { posts ->
                    posts.map { PostMapper.toDomain(it) }
                }
    }

    override fun getPost(postId: Long): Single<Post> {
        return postDao.getById(postId)
                .map { PostMapper.toDomain(it) }
    }

    override fun getPostsByType(type: PostType): Flowable<List<Post>> {
        return postDao.getByType(type)
                .map { posts ->
                    posts.map { PostMapper.toDomain(it) }
                }
    }

    override fun savePost(post: Post): Completable {
        return Completable.defer {
            postDao.insert(PostMapper.fromDomain(post))
            Completable.complete()
        }
    }

    override fun removePost(postId: Long): Completable {
        return Completable.defer {
            postDao.deleteById(postId)
            Completable.complete()
        }
    }

    override fun updatePost(post: Post): Completable {
        return Completable.defer {
            postDao.update(PostMapper.fromDomain(post))
            Completable.complete()
        }
    }

    override fun clearData(): Completable {
        return Completable.defer {
            postDao.deleteAll()
            Completable.complete()
        }
    }

}