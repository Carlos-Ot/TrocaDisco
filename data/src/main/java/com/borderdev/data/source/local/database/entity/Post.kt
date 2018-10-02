package com.borderdev.data.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.borderdev.data.source.local.database.entity.enum.PostType

@Entity(tableName = "tb_post")
data class Post(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id")
        var id: Long,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "post_url")
        var postUrl: String,

        @ColumnInfo(name = "pub_date")
        var pubDate: String,

        @ColumnInfo(name = "img_url")
        var imgUrl: String,

        @ColumnInfo(name = "post_type")
        var postType: PostType
) {
        @Ignore
        constructor(title: String = "",
                    postUrl: String = "",
                    pubDate: String = "",
                    imgUrl: String = "",
                    postType: PostType = PostType.DEFAULT
        ) : this (0, title, postUrl, pubDate, imgUrl, postType)
}