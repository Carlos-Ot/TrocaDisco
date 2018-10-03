package com.borderdev.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class EpisodeCategories (
        @Embedded var episode: Episode = Episode(),

        @Relation(parentColumn = "_id", entityColumn = "episode_id", entity = Category::class)
        var categories: List<Category> = emptyList()
)