package com.borderdev.data.local.database.entity

import androidx.room.*
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Category

@Entity(tableName = "tb_episode")
data class EpisodeEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        var id: Long = 0,

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "episode_number")
        var episodeNumber: Int = 0,

        @ColumnInfo(name = "description")
        var description: String = "",

        @ColumnInfo(name = "content")
        var content: String = "",

        @ColumnInfo(name = "pub_date")
        var pubDate: String = "",

        @ColumnInfo(name = "download_url")
        var downloadUrl: String = "",

        @ColumnInfo(name = "downloaded")
        var downloaded: Boolean = false,

        @ColumnInfo(name = "download_path")
        var downloadPath: String = "",

        @ColumnInfo(name = "episode_type")
        var episodeType: EpisodeType = EpisodeType.DEFAULT,

        @Ignore
        var categories: List<Category> = emptyList()

)