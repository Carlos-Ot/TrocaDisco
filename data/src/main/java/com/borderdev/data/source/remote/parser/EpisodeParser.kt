package com.borderdev.data.source.remote.parser

import com.borderdev.data.source.local.database.EpiType
import com.borderdev.data.source.local.database.entity.Episode
import com.borderdev.data.source.local.database.entity.enum.EpisodeType
import com.borderdev.data.source.remote.network.entity.Item
import org.jsoup.Jsoup

object EpisodeParser: Parser<Item, Episode> {
    override fun parse(remote: Item): Episode {
        return Episode(
                title = remote.title,
                episodeNumber = getEpisodeNumber(remote.title),
                description = remote.description,
                pubDate = remote.pubDate,
                downloadUrl = getDownloadUrl(remote.content),
                downloaded = false,
                downloadPath = "",
                type = getEpisodeType(remote.title)
        )
    }

    private fun getEpisodeNumber(title: String): Int {
        val regex = Regex("#[\\d]+")
        val number = regex.find(title)
        val episodeNumber = number?.value ?: "00"
        episodeNumber.replace("#",  "")

        return episodeNumber.toInt()
    }

    private fun getDownloadUrl(content: String): String {
        var audioSrc: String = ""

        val doc = Jsoup.parse(content)
        val audioElement = doc.body().children().select("audio").first()
        if (audioElement != null) {
            audioSrc = audioElement.attr("src")
        }

        return audioSrc
    }

    private fun getEpisodeType(title: String) : EpiType {
        val substrings = title.split("#")
        val stringType = substrings.first()

        var episodeType = EpisodeType.DEFAULT

        if (stringType.equals("Troca o Disco")) {
            episodeType = EpisodeType.PODCAST
        } else {
            episodeType = EpisodeType.ALBUM_REVIEW
        }

        return EpiType.DEFAULT
    }
}