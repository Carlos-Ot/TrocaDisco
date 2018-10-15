package com.borderdev.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class EpisodeCategories (
        @Embedded var episode: EpisodeEntity = EpisodeEntity(),

        @Relation(parentColumn = "_id", entityColumn = "episode_id", entity = CategoryEntity::class)
        var categoryEntities: List<CategoryEntity> = emptyList()
)