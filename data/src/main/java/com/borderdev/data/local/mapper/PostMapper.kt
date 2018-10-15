package com.borderdev.data.local.mapper

import com.borderdev.data.local.database.entity.PostEntity
import com.borderdev.domain.model.Post

object PostMapper: Mapper<PostEntity, Post> {
    override fun toDomain(entity: PostEntity): Post {
        return Post(
                id = entity.id,
                title = entity.title,
                postUrl = entity.postUrl,
                pubDate = entity.pubDate,
                imgUrl = entity.imgUrl,
                postType = entity.postType

        )
    }

    override fun fromDomain(domain: Post): PostEntity {
        return PostEntity(
                id = domain.id,
                title = domain.title,
                postUrl = domain.postUrl,
                pubDate = domain.pubDate,
                imgUrl = domain.imgUrl,
                postType = domain.postType
        )
    }
}