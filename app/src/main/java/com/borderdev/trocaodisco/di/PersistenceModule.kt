package com.borderdev.trocaodisco.di

import com.borderdev.data.local.database.AppDatabase
import com.borderdev.data.remote.network.ArticleApi
import com.borderdev.data.remote.network.FeedApi
import com.borderdev.data.remote.network.ServiceClient
import com.borderdev.domain.datasource.PodcastLocalDataSource
import com.borderdev.domain.datasource.PodcastRemoteDataSource
import com.borderdev.domain.datasource.PostLocalDataSource
import com.borderdev.domain.datasource.PostRemoteDataSource
import com.borderdev.domain.repository.PodcastRepository
import com.borderdev.domain.repository.PostRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.*


val persistenceModule = Kodein.Module(name = "persistence_module") {

    //Local Bindings
    bind<AppDatabase>() with eagerSingleton {
        AppDatabase.getInstance(instance())
    }

    bind<PodcastLocalDataSource>() with singleton {
        com.borderdev.data.local.PodcastLocalDataSource(instance())
    }

    bind<PostLocalDataSource>() with singleton {
        com.borderdev.data.local.PostLocalDataSource(instance())
    }

    //Remote Bindings
    bind<FeedApi>() with singleton {
        ServiceClient.getFeedClient()
    }

    bind<ArticleApi>() with singleton {
        ServiceClient.getArticleClient()
    }

    bind<PodcastRemoteDataSource>() with singleton {
        com.borderdev.data.remote.PodcastRemoteDataSource(instance())
    }

    bind<PostRemoteDataSource>() with singleton {
        com.borderdev.data.remote.PostRemoteDataSource(instance())
    }

    //Repository Bindings
    bind<PodcastRepository>() with singleton {
        com.borderdev.data.repository.PodcastRepository(instance(), instance())
    }

    bind<PostRepository>() with singleton {
        com.borderdev.data.repository.PostRepository(instance(), instance())
    }

}