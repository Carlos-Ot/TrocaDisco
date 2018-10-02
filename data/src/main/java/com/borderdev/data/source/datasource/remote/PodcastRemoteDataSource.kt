package com.borderdev.data.source.datasource.remote

import com.borderdev.data.source.local.database.entity.Episode
import io.reactivex.Flowable
import io.reactivex.Observable

interface PodcastRemoteDataSource {
    fun getEpisodes(): Observable<List<Episode>>
}