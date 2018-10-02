package com.borderdev.data.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.borderdev.data.source.local.database.entity.enum.EpisodeType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EpisodeDaoTest : BaseTestDatabase() {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    val numberOfEpisodes = 10

    @Test
    fun getAllTest() {
        populateEpisodes(numberOfEpisodes)

        episodeDao.getAll()
                .test()
                .assertValue {
                    it.isNotEmpty()
                }
    }

    @Test
    fun getPodcastTest() {
        populateEpisodesAndCategories(numberOfEpisodes)

        val episodes = episodeDao.getByType(EpisodeType.PODCAST.code).blockingFirst()

        for (episode in episodes) {
            assertEquals(EpisodeType.PODCAST.code, episode.episodeType)
        }
    }

    @Test
    fun getReviewTest() {
        populateEpisodesAndCategories(numberOfEpisodes)

        val episodes = episodeDao.getByType(EpisodeType.ALBUM_REVIEW.code).blockingFirst()

        for (episode in episodes) {
            assertEquals(EpisodeType.ALBUM_REVIEW.code, episode.episodeType)
        }

    }

    @Test
    fun getByIdTest() {
        val _episode = createEpisode()

        val episodeId = episodeDao.insert(_episode)

        episodeDao.getById(episodeId)
                .test()
                .assertValue {
                    it.id.equals(episodeId)
                }
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

        val _episodes = episodeDao.getAll().blockingFirst().first()

        _episodes.description = newDescription
        val episodeId = _episodes.id

        episodeDao.update(_episodes)

        episodeDao.getById(episodeId)
                .test()
                .assertValue {
                    it.description == newDescription
                }

    }

    @Test
    fun updateListTest() {
        val episodeIds = mutableListOf<Long>()
        val newDescription = "New Description Test"
        populateEpisodesAndCategories(numberOfEpisodes)

        for (i in 0..2){
            episodeIds.add(episodeDao.insert(createEpisode()))
        }

        val _episode1 = episodeDao.getById(episodeIds[0]).blockingGet()
        _episode1.description = newDescription
        val _episode2 = episodeDao.getById(episodeIds[1]).blockingGet()
        _episode2.description = newDescription
        val _episode3 = episodeDao.getById(episodeIds[2]).blockingGet()
        _episode3.description = newDescription

        episodeDao.update(_episode1, _episode2, _episode3)

        val episodes = episodeDao.getAll().blockingFirst()

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

        episodeDao.getById(episodeId)
                .test()
                .assertNoValues()
    }

    @Test
    fun deleteAllTest() {
        populateEpisodesAndCategories(numberOfEpisodes)

        episodeDao.deleteAll()

        episodeDao.getAll()
                .test()
                .assertValue {
                    it.isEmpty()
                }
    }

}