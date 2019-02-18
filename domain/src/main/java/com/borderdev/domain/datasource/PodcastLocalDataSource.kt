package com.borderdev.domain.datasource

import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface PodcastLocalDataSource {

    fun getEpisodes(): Flowable<List<Episode>>

    fun getEpisodesByType(type: EpisodeType): Flowable<List<Episode>>

    fun getEpisode(episodeId: Long): Single<Episode>

    fun saveEpisode(episode: Episode): Completable

    fun removeEpisode(episodeId: Long): Completable

    fun updateEpisode(episode: Episode): Completable

    fun clearData(): Completable
}