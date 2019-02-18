package com.borderdev.domain.repository

import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PodcastRepository {

    fun loadEpisodes(): Completable

    fun getEpisodes(): Flowable<List<Episode>>

    fun getEpisodesByType(type: EpisodeType): Flowable<List<Episode>>

    fun getEpisode(episodeId: Long): Single<Episode>

    fun updateEpisode(episode: Episode): Completable

    fun clearData(): Completable
}