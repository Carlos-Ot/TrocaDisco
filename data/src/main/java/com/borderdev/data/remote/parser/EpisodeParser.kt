package com.borderdev.data.remote.parser

import com.borderdev.data.local.database.entity.Episode
import com.borderdev.data.local.database.enums.EpisodeType
import com.borderdev.data.remote.network.entity.Item
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
                episodeType = getEpisodeType(remote.title)
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
}