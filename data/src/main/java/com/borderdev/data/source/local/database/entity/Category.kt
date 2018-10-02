package com.borderdev.data.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "tb_category")
data class Category (

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        var id: Long,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "episode_id")
        var episodeId: Long
) {
        @Ignore
        constructor(name: String = "", episodeId: Long = 0) : this (0, name, episodeId)
}
