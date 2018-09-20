package com.borderdev.data.database

import android.support.test.runner.AndroidJUnit4
import com.borderdev.data.entity.Category
import com.borderdev.data.entity.EpisodeCategories
import com.borderdev.data.entity.enum.EpisodeType
import com.natpryce.hamkrest.assertion.assert
import com.natpryce.hamkrest.isEmpty
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EpisodeDaoTest : BaseTestDatabase() {

    val numberOfEpisodes = 10

    @Test
    fun getAllTest() {
        populateEpisodesAndCategories(numberOfEpisodes)

        val episodes = episodeDao.getAll()

        assertTrue(episodes.isNotEmpty())
    }

    @Test
    fun getByTypeTest() {
        populateEpisodesAndCategories(numberOfEpisodes)

        val podcasts = episodeDao.getByType(EpisodeType.PODCAST.code)

        for (podcast in podcasts) {
            assertEquals(podcast.type, EpisodeType.PODCAST.code)
        }

        val albumReviews = episodeDao.getByType(EpisodeType.ALBUM_REVIEW.code)

        for (review in albumReviews) {
            assertEquals(EpisodeType.ALBUM_REVIEW.code, review.type)
        }
    }

    @Test
    fun getByIdTest() {
        val _episode = createEpisode()

        val episodeId = episodeDao.insert(_episode)

        val episode = episodeDao.getById(episodeId)

        assertEquals(episodeId, episode.id)
    }

    @Test
    fun insertTest() {
        val episode = createEpisode()

        val episodeId = episodeDao.insert(episode)

        assertNotEquals(0, episodeId)
    }

    @Test
    fun insertListTest() {
        val insertedEpisodes = episodeDao.insert(
                createEpisode(),
                createEpisode(),
                createEpisode()
        )
        assertEquals(3, insertedEpisodes.size)
    }

    @Test
    fun updateTest() {
        val newDescription = "New Description Test"
        populateEpisodesAndCategories(numberOfEpisodes)

        val _episodes = episodeDao.getAll()

        _episodes.first().description = newDescription
        val episodeId = _episodes.first().id

        episodeDao.update(_episodes.first())

        val episode = episodeDao.getById(episodeId)

        assertEquals(newDescription, episode.description)
    }

    @Test
    fun updateListTest() {
        val episodeIds = mutableListOf<Long>()
        val newDescription = "New Description Test"
        populateEpisodesAndCategories(numberOfEpisodes)

        for (i in 0..2){
            episodeIds.add(episodeDao.insert(createEpisode()))
        }

        val _episode1 = episodeDao.getById(episodeIds[0])
        _episode1.description = newDescription
        val _episode2 = episodeDao.getById(episodeIds[1])
        _episode2.description = newDescription
        val _episode3 = episodeDao.getById(episodeIds[2])
        _episode3.description = newDescription

        episodeDao.update(_episode1, _episode2, _episode3)

        val episodes = episodeDao.getAll()

        for (episode in episodes) {
            for (episodeId in episodeIds) {
                if (episode.id == episodeId) {
                    assertEquals(newDescription, episode.description)
                }
            }
        }

    }

    @Test
    fun deleteByIdTest() {
        populateEpisodesAndCategories(numberOfEpisodes)

        val _episode = createEpisode()

        val episodeId = episodeDao.insert(_episode)

        episodeDao.deleteById(episodeId)

        val episodes = episodeDao.getAll()

        for (episode in episodes) {
            assertNotEquals(episodeId, episode.id)
        }
    }

    @Test
    fun deleteAllTest() {
        populateEpisodesAndCategories(numberOfEpisodes)

        episodeDao.deleteAll()

        val episodes = episodeDao.getAll()

        assert.that(episodes, isEmpty)
    }

    @Test
    fun writeEpisodeWithCategoryAndRead() {
        val episode = createEpisode()

        val episodeId = episodeDao.insert(episode)

        val category = categoryDao.insert(Category(name = "podcast", episodeId = episodeId))

        val episodes: List<EpisodeCategories> = episodeCategoriesDao.getEpisodesWithCategories()

        assertNotEquals("", episodes.first().episode.title)
        assertEquals(category, episodes.first().categories.first().id)
    }

}