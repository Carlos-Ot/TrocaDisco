package com.borderdev.data.local.mapper

import com.borderdev.data.local.database.entity.CategoryEntity
import com.borderdev.data.local.database.entity.EpisodeEntity
import com.borderdev.domain.model.Category
import com.borderdev.domain.model.Episode

object EpisodeMapper: Mapper<EpisodeEntity, Episode> {

    override fun toDomain(entity: EpisodeEntity): Episode {
        return Episode(
                id = entity.id,
                title = entity.title,
                episodeNumber = entity.episodeNumber,
                description = entity.description,
                content = entity.content,
                pubDate = entity.pubDate,
                downloadUrl = entity.downloadUrl,
                downloaded = entity.downloaded,
                downloadPath = entity.downloadPath,
                episodeType = entity.episodeType,
                categories = entity.categories
        )
    }

    override fun fromDomain(domain: Episode): EpisodeEntity {
        return EpisodeEntity(
                id = domain.id,
                title = domain.title,
                episodeNumber = domain.episodeNumber,
                description = domain.description,
                content = domain.content,
                pubDate = domain.pubDate,
                downloadUrl = domain.downloadUrl,
                downloaded = domain.downloaded,
                downloadPath = domain.downloadPath,
                episodeType = domain.episodeType,
                categories = domain.categories
        )
    }
}