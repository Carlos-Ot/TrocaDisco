package com.borderdev.domain.datasource

import com.borderdev.domain.model.Episode
import io.reactivex.Flowable

interface PodcastRemoteDataSource {
    fun getEpisodes(): Flowable<List<Episode>>
}