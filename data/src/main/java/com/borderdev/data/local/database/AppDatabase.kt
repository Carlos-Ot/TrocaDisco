package com.borderdev.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.borderdev.data.local.database.converters.EpisodeTypeConverter
import com.borderdev.data.local.database.dao.CategoryDao
import com.borderdev.data.local.database.dao.EpisodeCategoriesDao
import com.borderdev.data.local.database.dao.EpisodeDao
import com.borderdev.data.local.database.dao.PostDao
import com.borderdev.data.local.database.entity.CategoryEntity
import com.borderdev.data.local.database.entity.EpisodeEntity
import com.borderdev.data.local.database.entity.PostEntity

@Database(entities = [EpisodeEntity::class, PostEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(EpisodeTypeConverter::class)
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