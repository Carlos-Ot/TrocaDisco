package com.borderdev.trocaodisco.di

import com.borderdev.presentation.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val presentationModule = Kodein.Module("presentation_module") {

    bind<EpisodeListViewModel>() with provider {
        EpisodeListViewModel(instance())
    }

    bind<EpisodeViewModel>() with provider {
        EpisodeViewModel(instance())
    }

    bind<PostListViewModel>() with provider {
        PostListViewModel(instance())
    }

    bind<PostViewModel>() with provider {
        PostViewModel(instance())
    }

    bind<UpdateEpisodeViewModel>() with provider {
        UpdateEpisodeViewModel(instance())
    }
}