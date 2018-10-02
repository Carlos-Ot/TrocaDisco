package com.borderdev.data.source.remote.parser

import com.borderdev.data.source.local.database.entity.Post
import com.borderdev.data.source.remote.network.entity.Article

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