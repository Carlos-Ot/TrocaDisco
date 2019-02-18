package com.borderdev.data.repository

import android.util.Log
import com.borderdev.domain.datasource.PodcastLocalDataSource
import com.borderdev.domain.datasource.PodcastRemoteDataSource
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PodcastRepository(
        private val remoteDataSource: PodcastRemoteDataSource,
        private val localDataSource: PodcastLocalDataSource
): PodcastRepository {

    override fun loadEpisodes(): Completable {
        return remoteDataSource.getEpisodes()
                .flatMap { episodes ->
                    episodes.forEach {
                        Log.d("XABLAU", "episode: ${it}")
                        localDataSource.saveEpisode(it)
                    }
                    Flowable.just(episodes)
                }.ignoreElements()
    }

    override fun getEpisodes(): Flowable<List<Episode>> {
        return localDataSource.getEpisodes()
    }

    override fun getEpisodesByType(type: EpisodeType): Flowable<List<Episode>> {
        return localDataSource.getEpisodesByType(type)
    }

    override fun getEpisode(episodeId: Long): Single<Episode> {
        return localDataSource.getEpisode(episodeId)
    }

    override fun updateEpisode(episode: Episode): Completable {
        return localDataSource.updateEpisode(episode)
    }

    override fun clearData(): Completable {
        return localDataSource.clearData()
    }
}