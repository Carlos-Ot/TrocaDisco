package com.borderdev.data.source.local

import com.borderdev.data.source.datasource.local.PostLocalDataSource
import com.borderdev.data.source.local.database.EpiType
import com.borderdev.data.source.local.database.entity.Post
import com.borderdev.data.source.local.database.dao.PostDao
import com.borderdev.data.source.local.database.entity.enum.PostType
import io.reactivex.Flowable
import io.reactivex.Single

class PostLocalDataSource(private val postDao: PostDao) : PostLocalDataSource {

    override fun getPosts(): Flowable<List<Post>> {
        return postDao.getAll()
    }

    override fun getPost(postId: Long): Single<Post> {
        return postDao.getById(postId)
    }

    override fun getPostsByType(type: EpiType): Flowable<List<Post>> {
        return postDao.getByType(type)
    }

    override fun savePost(post: Post) {
        postDao.insert(post)
    }

    override fun removePost(postId: Long) {
        postDao.deleteById(postId)
    }

    override fun updatePost(post: Post) {
        postDao.update(post)
    }

    override fun clearData() {
        postDao.deleteAll()
    }
}