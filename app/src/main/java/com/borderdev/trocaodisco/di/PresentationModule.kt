package com.borderdev.trocaodisco.di

import com.borderdev.presentation.*
import com.borderdev.presentation.main.MainPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val presentationModule = Kodein.Module("presentation_module") {

    bind<MainPresenter>() with provider {
        MainPresenter(instance())
    }
}