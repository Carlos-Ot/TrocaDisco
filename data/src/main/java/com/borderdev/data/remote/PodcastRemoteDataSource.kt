package com.borderdev.data.remote

import com.borderdev.data.datasource.remote.PodcastRemoteDataSource
import com.borderdev.data.local.database.entity.Episode
import com.borderdev.data.remote.network.FeedApi
import com.borderdev.data.remote.parser.EpisodeParser
import io.reactivex.Flowable
import io.reactivex.Observable

class PodcastRemoteDataSource(private val apiClient: FeedApi) : PodcastRemoteDataSource {

    override fun getEpisodes(): Observable<List<Episode>> {
        return apiClient.getPodcasts()
                .map {feed ->
                    val items = feed.channel.items
                    val episodes: MutableList<Episode> = mutableListOf()
                    items.forEach {
                        val episode = EpisodeParser.parse(it)
                        episodes.add(episode)
                    }
                    episodes.toList()
                }
    }
}