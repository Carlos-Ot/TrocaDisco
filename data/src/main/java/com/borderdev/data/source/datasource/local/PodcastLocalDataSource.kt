package com.borderdev.data.source.datasource.local

import com.borderdev.data.source.local.database.EpiType
import com.borderdev.data.source.local.database.entity.Episode
import io.reactivex.Flowable
import io.reactivex.Single

interface PodcastLocalDataSource {

    fun getEpisodes(): Flowable<List<Episode>>

    fun getEpisodesByType(type: EpiType): Flowable<List<Episode>>

    fun getEpisode(episodeId: Long): Single<Episode>

    fun saveEpisode(episode: Episode)

    fun removeEpisode(episodeId: Long)

    fun updateEpisode(episode: Episode)

    fun clearData()
}