package com.borderdev.data.source.datasource

import com.borderdev.data.entity.Post

interface HighlightsDataSource {

    fun getPosts(): List<Post>

    fun getPost(postId: Int): Post

    fun savePost(post: Post)

    fun removePost(postId: Int)

    fun updatePost(post: Post)
}