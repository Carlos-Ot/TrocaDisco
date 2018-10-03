package com.borderdev.domain.repository

import com.borderdev.data.local.database.entity.Episode
import com.borderdev.data.local.database.entity.EpisodeCategories
import com.borderdev.data.local.database.enums.EpisodeType
import io.reactivex.Flowable
import io.reactivex.Single

interface PodcastRepository {


    fun getEpisodes(): Flowable<List<Episode>>

    fun getEpisodesCategories(): Flowable<List<EpisodeCategories>>

    fun getEpisodesCategoriesByTipe(episodeType: EpisodeType): Flowable<List<EpisodeCategories>>

    fun getEpisodeCategoryById(episodeId: Long): EpisodeCategories

    fun getEpisodesByType(type: EpisodeType): Flowable<List<Episode>>

    fun getEpisode(episodeId: Long): Single<Episode>

    fun saveEpisode(episode: Episode)

    fun removeEpisode(episodeId: Long)

    fun updateEpisode(episode: Episode)

    fun clearData()
}