package com.borderdev.data.local

import com.borderdev.data.datasource.local.PostLocalDataSource
import com.borderdev.data.local.database.entity.Post
import com.borderdev.data.local.database.dao.PostDao
import com.borderdev.data.local.database.enums.PostType
import io.reactivex.Flowable
import io.reactivex.Single

class PostLocalDataSource(private val postDao: PostDao) : PostLocalDataSource {

    override fun getPosts(): Flowable<List<Post>> {
        return postDao.getAll()
    }

    override fun getPost(postId: Long): Single<Post> {
        return postDao.getById(postId)
    }

    override fun getPostsByType(type: PostType): Flowable<List<Post>> {
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