package com.borderdev.data.local.mapper

import com.borderdev.data.local.database.entity.CategoryEntity
import com.borderdev.domain.model.Category

object CategoryMapper: Mapper<CategoryEntity, Category> {
    override fun toDomain(entity: CategoryEntity): Category {
        return Category(
                id = entity.id,
                name = entity.name,
                episodeId = entity.episodeId
        )
    }

    override fun fromDomain(domain: Category): CategoryEntity {
        return CategoryEntity(
                id = domain.id,
                name = domain.name,
                episodeId = domain.episodeId
        )
    }
}