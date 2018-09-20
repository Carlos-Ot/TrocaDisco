package com.borderdev.data.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

data class EpisodeCategories (
        @Embedded var episode: Episode = Episode(),

        @Relation(parentColumn = "_id", entityColumn = "episode_id", entity = Category::class)
        var categories: List<Category> = emptyList()
)