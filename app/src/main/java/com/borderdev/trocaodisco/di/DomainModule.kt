package com.borderdev.trocaodisco.di

import com.borderdev.domain.schedulers.BaseScheduler
import com.borderdev.domain.schedulers.SchedulerProvider
import com.borderdev.domain.schedulers.TestSchedulerProvider
import com.borderdev.domain.usecases.episode.*
import com.borderdev.domain.usecases.post.GetPost
import com.borderdev.domain.usecases.post.GetPosts
import com.borderdev.domain.usecases.post.GetPostsByType
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

const val MAIN_SCHEDULER_TAG = "main_schedulers"
const val TEST_SCHEDULER_TAG = "test_schedulers"
val domainModule = Kodein.Module("domain_module") {

    //Scheduler Bindings
    bind<BaseScheduler>(tag = MAIN_SCHEDULER_TAG) with provider {
        SchedulerProvider()
    }

    bind<BaseScheduler>(tag = TEST_SCHEDULER_TAG) with provider {
        TestSchedulerProvider()
    }

    //UseCases Bindings
    bind<GetEpisode>() with singleton {
        GetEpisode(instance(), instance(MAIN_SCHEDULER_TAG))
    }

    bind<GetEpisodeByType>() with singleton {
        GetEpisodeByType(instance(), instance(MAIN_SCHEDULER_TAG))
    }

    bind<GetEpisodes>() with singleton {
        GetEpisodes(instance(), instance(MAIN_SCHEDULER_TAG))
    }

    bind<UpdateEpisode>() with singleton {
        UpdateEpisode(instance(), instance(MAIN_SCHEDULER_TAG))
    }

    bind<GetPost>() with singleton {
        GetPost(instance(), instance(MAIN_SCHEDULER_TAG))
    }

    bind<GetPosts>() with singleton {
        GetPosts(instance(), instance(MAIN_SCHEDULER_TAG))
    }

    bind<GetPostsByType>() with singleton {
        GetPostsByType(instance(), instance(MAIN_SCHEDULER_TAG))
    }

    bind<LoadEpisodes>() with singleton {
        LoadEpisodes(instance(), instance(MAIN_SCHEDULER_TAG))
    }
}