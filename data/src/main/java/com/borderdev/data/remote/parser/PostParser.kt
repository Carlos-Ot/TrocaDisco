package com.borderdev.data.remote.parser

import com.borderdev.data.remote.network.entity.Article
import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Post

object PostParser: Parser<Article, Post> {

    override fun parse(remote: Article): Post {
        return Post (
                id = 0,
                title = remote.title,
                imgUrl = remote.imgUrl,
                postUrl = remote.postUrl,
                pubDate = remote.pubDate,
                postType = PostType.DEFAULT
        )
    }

}