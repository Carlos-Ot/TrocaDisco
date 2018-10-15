package com.borderdev.data.local.database.converters

import androidx.room.TypeConverter
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.enums.PostType

class EpisodeTypeConverter {

    @TypeConverter
    fun fromEpisodeType(value: EpisodeType): Int {
        return value.let { value.ordinal }
    }

    @TypeConverter
    fun toEpisodeType(value: Int): EpisodeType {
        return EpisodeType.values()[value]
    }

    @TypeConverter
    fun fromPostType(value: PostType): Int {
        return value.let { value.ordinal }
    }

    @TypeConverter
    fun toPostType(value: Int): PostType {
        return PostType.values()[value]
    }
}