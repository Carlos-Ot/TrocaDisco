package com.borderdev.data.remote.parser

import com.borderdev.data.remote.network.entity.RemoteCategory
import com.borderdev.domain.model.Category

object CategoryParser: Parser<RemoteCategory, Category> {
    override fun parse(remote: RemoteCategory): Category {
        return Category(name = remote.name)
    }
}