package com.borderdev.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.borderdev.data.source.local.database.converters.EnumTypeConverters
import com.borderdev.data.source.local.database.dao.CategoryDao
import com.borderdev.data.source.local.database.dao.EpisodeCategoriesDao
import com.borderdev.data.source.local.database.dao.EpisodeDao
import com.borderdev.data.source.local.database.dao.PostDao
import com.borderdev.data.source.local.database.entity.Category
import com.borderdev.data.source.local.database.entity.Episode
import com.borderdev.data.source.local.database.entity.Post

@Database(entities = [Episode::class, Post::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(EnumTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun episodeDao(): EpisodeDao

    abstract fun postDao(): PostDao

    abstract fun episodeCategoriesDao(): EpisodeCategoriesDao

    abstract fun categoryDao(): CategoryDao

    companion object {
        private var TEST_DATABASE = false
        private const val databaseName = "app_database"

        private var instance: AppDatabase? = null

        private val lock = Any()

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (instance == null) {
                    if (TEST_DATABASE) {
                        instance = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
                    } else {
                        instance = Room.databaseBuilder(context, AppDatabase::class.java, databaseName).allowMainThreadQueries().build()
                    }
                }

                return instance!!
            }
        }

        fun setupDatabase(isTesting: Boolean) {
            TEST_DATABASE = isTesting
        }
    }
}