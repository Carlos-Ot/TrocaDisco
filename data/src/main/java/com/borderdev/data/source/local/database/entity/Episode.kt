package com.borderdev.data.source.local.database.entity

import androidx.room.*
import com.borderdev.data.source.local.database.converters.EnumTypeConverters
import com.borderdev.data.source.local.database.entity.enum.EpisodeType

@Entity(tableName = "tb_episode")
data class Episode(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        var id: Long,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "episode_number")
        var episodeNumber: Int,

        @ColumnInfo(name = "description")
        var description: String,

        @ColumnInfo(name = "pub_date")
        var pubDate: String,

        @ColumnInfo(name = "download_url")
        var downloadUrl: String,

        @ColumnInfo(name = "downloaded")
        var downloaded: Boolean,

        @ColumnInfo(name = "download_path")
        var downloadPath: String,

        @ColumnInfo(name = "episode_type")
        var episodeType: EpisodeType
) {
    @Ignore
    constructor(title: String = "",
                episodeNumber: Int = 0,
                description: String = "",
                pubDate: String = "",
                downloadUrl: String = "",
                downloaded: Boolean = false,
                downloadPath: String = "",
                type: EpisodeType = EpisodeType.DEFAULT
    ) : this (0, title, episodeNumber, description, pubDate, downloadUrl, downloaded, downloadPath, type)
}