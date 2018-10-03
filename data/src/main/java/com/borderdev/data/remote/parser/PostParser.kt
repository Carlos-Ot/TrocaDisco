package com.borderdev.data.remote.parser

import com.borderdev.data.local.database.entity.Post
import com.borderdev.data.remote.network.entity.Article

object PostParser: Parser<Article, Post> {

    override fun parse(remote: Article): Post {
        return Post (
                title = remote.title,
                imgUrl = remote.imgUrl,
                postUrl = remote.postUrl,
                pubDate = remote.pubDate
        )
    }

}