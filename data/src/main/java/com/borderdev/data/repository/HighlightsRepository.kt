package com.borderdev.data.repository

import com.borderdev.data.entity.Post
import com.borderdev.data.source.datasource.HighlightsDataSource

class HighlightsRepository : HighlightsDataSource {

    override fun getPosts(): List<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPost(postId: Int): Post {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePost(post: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removePost(postId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updatePost(post: Post) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}