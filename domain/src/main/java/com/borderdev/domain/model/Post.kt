package com.borderdev.domain.model

import com.borderdev.domain.enums.PostType

data class Post (
        var id: Long = 0,

        var title: String = "",

        var postUrl: String = "",

        var pubDate: String = "",

        var imgUrl: String = "",

        var postType: PostType = PostType.DEFAULT
)