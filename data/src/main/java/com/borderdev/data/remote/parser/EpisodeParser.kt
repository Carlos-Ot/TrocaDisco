package com.borderdev.data.remote.parser

import com.borderdev.data.local.database.entity.CategoryEntity
import com.borderdev.data.remote.network.entity.Item
import com.borderdev.data.remote.network.entity.RemoteCategory
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Category
import com.borderdev.domain.model.Episode
import org.jsoup.Jsoup

object EpisodeParser: Parser<Item, Episode> {

    val numberRegex = "#[\\d]+"
    val defaultNumber = "00"
    val numberDelimiter = "#"
    val emptyString = ""

    val audioTag = "audio"
    val audioAttr = "src"

    val titlePodcastType = "Troca o Disco"

    override fun parse(remote: Item): Episode {
        return Episode(
                title = remote.title,
                episodeNumber = getEpisodeNumber(remote.title),
                description = remote.description,
                content = remote.content,
                pubDate = remote.pubDate,
                downloadUrl = getDownloadUrl(remote.content),
                downloaded = false,
                downloadPath = "",
                episodeType = getEpisodeType(remote.title),
                categories = getCategories(remote.categories)
        )
    }

    private fun getEpisodeNumber(title: String): Int {
        val regex = Regex(numberRegex)
        val number = regex.find(title)
        val episodeNumber = number?.value ?: defaultNumber
        episodeNumber.replace(numberDelimiter,  emptyString)

        return episodeNumber.toInt()
    }

    private fun getDownloadUrl(content: String): String {
        var audioSrc = emptyString

        val doc = Jsoup.parse(content)
        val audioElement = doc.body().children().select(audioTag).first()
        if (audioElement != null) {
            audioSrc = audioElement.attr(audioAttr)
        }

        return audioSrc
    }

    private fun getEpisodeType(title: String) : EpisodeType {
        val substrings = title.split(numberDelimiter)
        val stringType = substrings.first()

        val episodeType : EpisodeType

        if (stringType.equals(titlePodcastType)) {
            episodeType = EpisodeType.PODCAST
        } else {
            episodeType = EpisodeType.ALBUM_REVIEW
        }

        return episodeType
    }

    private fun getCategories(remoteCategories: List<RemoteCategory>): List<Category> {
        val categories: MutableList<Category> = mutableListOf()

        remoteCategories.forEach {
            val category = CategoryParser.parse(it)
            categories.add(category)
        }
        return categories.toList()
    }
}