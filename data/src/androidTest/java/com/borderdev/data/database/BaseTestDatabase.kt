package com.borderdev.data.database

import android.content.Context
import android.support.test.InstrumentationRegistry
import com.borderdev.data.entity.Category
import com.borderdev.data.entity.Episode
import com.borderdev.data.entity.Post
import com.borderdev.data.entity.enum.EpisodeType
import com.borderdev.data.source.local.database.AppDatabase
import com.borderdev.data.source.local.database.dao.CategoryDao
import com.borderdev.data.source.local.database.dao.EpisodeCategoriesDao
import com.borderdev.data.source.local.database.dao.EpisodeDao
import com.borderdev.data.source.local.database.dao.PostDao
import org.junit.After
import org.junit.AfterClass
import org.junit.BeforeClass
import java.util.*

abstract class BaseTestDatabase {

    private val episodeNames = arrayOf(
            "Conhecendo a música Pop",
            "Rock virou genero de nicho?",
            "Clube da Esquina",
            "A Origem da Bossa Nova",
            "Música Brasileira",
            "A Nova Loira do Tchan",
            "Florence And The Machine",
            "Sertanejo Raiz",
            "Adolecentes 2000"
    )

    private val categoriesNames = arrayOf(
            "Podcast",
            "Comportamento",
            "Entretenimento",
            "Review",
            "História",
            "Rock",
            "Pop"
    )

    companion object {
        private lateinit var database: AppDatabase
        lateinit var episodeDao: EpisodeDao
        lateinit var episodeCategoriesDao: EpisodeCategoriesDao
        lateinit var categoryDao: CategoryDao
        lateinit var postDao: PostDao

        @BeforeClass
        @JvmStatic
        fun createDb() {
            val context: Context = InstrumentationRegistry.getTargetContext()

            AppDatabase.setupDatabase(isTesting = true)

            database = AppDatabase.getInstance(context)

            episodeDao = database.episodeDao()
            episodeCategoriesDao = database.episodeCategoriesDao()
            categoryDao = database.categoryDao()
            postDao = database.postDao()
        }
    }

    @After
    fun clearTables() {
        episodeDao.deleteAll()
        categoryDao.deleteAll()
        postDao.deleteAll()
    }


    fun createEpisode(): Episode = Episode(
            title = episodeNames.get(Random().nextInt(episodeNames.size)),
            episodeNumber = 1,
            description = "In this episode we will test the Database",
            pubDate = "18-09-2018",
            downloadUrl = "http://podcast.com",
            type = EpisodeType.DEFAULT.code)

    fun createCategory(episodeId: Long) = Category(name = "podcast", episodeId = episodeId)

    fun createPost() = Post(
            title = episodeNames.get(Random().nextInt(episodeNames.size)),
            postUrl = "http://post.com",
            pubDate = "18-09-2018",
            imgUrl = "http://post.com/img.jpg")

    fun populateEpisodesAndCategories(numberOfEpisodes: Int) {

        for (index in 0..numberOfEpisodes) {
            episodeDao.insert(
                    Episode(
                            title = episodeNames.get(Random().nextInt(episodeNames.size)),
                            episodeNumber = index,
                            description = "In this episode we will test the Database",
                            pubDate = "18-09-2018",
                            downloadUrl = "http://podcast.com",
                            type = Random().nextInt(EpisodeType.values().size))
            )
        }

        val episodes = episodeDao.getAll()

        for (episode in episodes) {
            categoryDao.insert(
                    Category(name = categoriesNames.get(Random().nextInt(categoriesNames.size)),
                            episodeId = episode.id),
                    Category(name = categoriesNames.get(Random().nextInt(categoriesNames.size)),
                            episodeId = episode.id),
                    Category(name = categoriesNames.get(Random().nextInt(categoriesNames.size)),
                            episodeId = episode.id),
                    Category(name = categoriesNames.get(Random().nextInt(categoriesNames.size)),
                            episodeId = episode.id)
            )
        }
    }

    fun populatePost(numberOfPosts: Int) {

        for (index in 0..numberOfPosts) {
            postDao.insert(
                    Post(
                            title = episodeNames.get(Random().nextInt(episodeNames.size)),
                            postUrl = "http://post.com",
                            pubDate = "18-09-2018",
                            imgUrl = "http://post.com/img.jpg")
            )
        }

    }

    fun populateCategory(numberOfCategories: Int) {
        populateEpisodesAndCategories(numberOfCategories * 2)

        val episodes = episodeDao.getAll()

        for (episode in episodes) {
            for (index in 0..numberOfCategories) {
                categoryDao.insert(
                        Category(name = categoriesNames.get(Random().nextInt(categoriesNames.size)),
                                episodeId = episode.id))
            }
        }
    }

}