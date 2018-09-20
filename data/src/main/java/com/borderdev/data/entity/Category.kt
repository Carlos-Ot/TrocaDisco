package com.borderdev.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

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
