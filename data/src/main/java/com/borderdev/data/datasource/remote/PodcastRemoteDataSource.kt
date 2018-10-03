package com.borderdev.data.datasource.remote

import com.borderdev.data.local.database.entity.Episode
import io.reactivex.Flowable
import io.reactivex.Observable

interface PodcastRemoteDataSource {
    fun getEpisodes(): Observable<List<Episode>>
}