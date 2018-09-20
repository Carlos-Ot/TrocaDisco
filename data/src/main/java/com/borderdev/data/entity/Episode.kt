package com.borderdev.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.borderdev.data.entity.enum.EpisodeType

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
        var type: Int
) {
    @Ignore
    constructor(title: String = "",
                episodeNumber: Int = 0,
                description: String = "",
                pubDate: String = "",
                downloadUrl: String = "",
                downloaded: Boolean = false,
                downloadPath: String = "",
                type: Int = EpisodeType.DEFAULT.code
    ) : this (0, title, episodeNumber, description, pubDate, downloadUrl, downloaded, downloadPath, type)
}