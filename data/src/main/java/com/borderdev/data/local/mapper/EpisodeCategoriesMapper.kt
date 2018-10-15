package com.borderdev.data.local.mapper

import com.borderdev.data.local.database.entity.CategoryEntity
import com.borderdev.data.local.database.entity.EpisodeCategories
import com.borderdev.data.local.database.entity.EpisodeEntity
import com.borderdev.domain.model.Category
import com.borderdev.domain.model.Episode

object EpisodeCategoriesMapper: Mapper<EpisodeCategories, Episode> {
    override fun toDomain(entity: EpisodeCategories): Episode {
        return Episode(
                id = entity.episode.id,
                title = entity.episode.title,
                episodeNumber = entity.episode.episodeNumber,
                description = entity.episode.description,
                content = entity.episode.content,
                pubDate = entity.episode.pubDate,
                downloadUrl = entity.episode.downloadUrl,
                downloaded = entity.episode.downloaded,
                downloadPath = entity.episode.downloadPath,
                episodeType = entity.episode.episodeType,
                categories = getCategoriesList(entity.categoryEntities)
        )
    }

    override fun fromDomain(domain: Episode): EpisodeCategories {
        return EpisodeCategories(
                EpisodeEntity(id = domain.id,
                title = domain.title,
                episodeNumber = domain.episodeNumber,
                description = domain.description,
                content = domain.content,
                pubDate = domain.pubDate,
                downloadUrl = domain.downloadUrl,
                downloaded = domain.downloaded,
                downloadPath = domain.downloadPath,
                episodeType = domain.episodeType
                ),
                categoryEntities = getCategoryEntitiesList(domain.categories)
                )
    }

    private fun getCategoriesList(categoryEntities: List<CategoryEntity>): List<Category> {
        return categoryEntities
                .map { CategoryMapper.toDomain(it) }
    }

    private fun getCategoryEntitiesList(categories: List<Category>): List<CategoryEntity> {
        return categories
                .map { CategoryMapper.fromDomain(it) }
    }
}