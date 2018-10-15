package com.borderdev.data.remote

import com.borderdev.data.remote.network.FeedApi
import com.borderdev.data.remote.parser.EpisodeParser
import com.borderdev.domain.datasource.PodcastRemoteDataSource
import com.borderdev.domain.model.Episode
import io.reactivex.Flowable

class PodcastRemoteDataSource(private val apiClient: FeedApi) : PodcastRemoteDataSource {

    override fun getEpisodes(): Flowable<List<Episode>> {
        return apiClient.getPodcasts()
                .map {feed ->
                    feed.channel.items
                            .map { EpisodeParser.parse(it) }
                }
    }
}