package com.borderdev.presentation.data_factory

import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.enums.PostType
import com.borderdev.domain.model.Category
import com.borderdev.domain.model.Episode
import com.borderdev.domain.model.Post

object DomainDataFactory {

    fun makeEpisode() = Episode(
            title = DataFactory.randomString(),
            episodeNumber = DataFactory.randomInt(),
            description = DataFactory.randomString(),
            content = DataFactory.randomString(),
            pubDate = DataFactory.randomDate(),
            downloadUrl = DataFactory.randomUrl(),
            downloaded = DataFactory.randomBoolean(),
            downloadPath = DataFactory.randomPath(),
            episodeType = EpisodeType.DEFAULT,
            categories = makeCategoriesList(4)

    )

    fun makeEpisodeList(count: Int): List<Episode> {
        val episodes = mutableListOf<Episode>()
        repeat(count) {
            episodes.add(makeEpisode())
        }

        return episodes
    }

    fun makeCategory() = Category(
            name = DataFactory.randomString(),
            episodeId = DataFactory.randomLong()
    )

    fun makeCategoriesList(count: Int): List<Category> {
        val categories = mutableListOf<Category>()

        repeat(count) {
            categories.add(makeCategory())
        }

        return categories
    }

    fun makePost() = Post(
            title = DataFactory.randomString(),
            postUrl = DataFactory.randomUrl(),
            pubDate = DataFactory.randomDate(),
            imgUrl = DataFactory.randomUrl(),
            postType = PostType.DEFAULT
    )

    fun makePostsList(count: Int): List<Post> {
        val posts = mutableListOf<Post>()

        repeat(count) {
            posts.add(makePost())
        }

        return posts
    }
}