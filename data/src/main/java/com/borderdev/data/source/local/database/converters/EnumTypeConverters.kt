package com.borderdev.data.source.local.database.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.borderdev.data.source.local.database.entity.enum.EpisodeType
import com.borderdev.data.source.local.database.entity.enum.PostType

class EnumTypeConverters {

    @TypeConverter
    fun toEpisodeType(value: Int): EpisodeType {
        return EpisodeType.values()[value]
    }

    @TypeConverter
    fun fromEpisodeType(value: EpisodeType): Int {
        return value.let { value.ordinal }
    }

    @TypeConverter
    fun toPostType(value: Int): PostType {
        return PostType.values()[value]
    }

    @TypeConverter
    fun fromPostType(value: PostType): Int {
        return value.let { value.ordinal }
    }

}