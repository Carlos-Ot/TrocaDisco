package com.borderdev.data.repository

import com.borderdev.domain.datasource.PodcastLocalDataSource
import com.borderdev.domain.datasource.PodcastRemoteDataSource
import com.borderdev.domain.enums.EpisodeType
import com.borderdev.domain.model.Episode
import com.borderdev.domain.repository.PodcastRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.intellij.lang.annotations.Flow

class PodcastRepository(
        private val remoteDataSource: PodcastRemoteDataSource,
        private val localDataSource: PodcastLocalDataSource
): PodcastRepository {

    override fun getEpisodes(): Flowable<List<Episode>> {
        return remoteDataSource.getEpisodes()
                .publish { remote ->
                    remote.flatMap { episodes ->
                        episodes.forEach {
                            localDataSource.saveEpisode(it)
                        }
                        Flowable.just(episodes)
                    }

                    Flowable.merge(remote, localDataSource.getEpisodes().takeUntil(remote))
                }
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