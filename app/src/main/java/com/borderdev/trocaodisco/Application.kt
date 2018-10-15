package com.borderdev.trocaodisco

import android.app.Application
import android.content.Context
import com.borderdev.trocaodisco.di.domainModule
import com.borderdev.trocaodisco.di.persistenceModule
import com.borderdev.trocaodisco.di.presentationModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class Application: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        bind<Context>() with singleton { this@Application.applicationContext }
        import(androidModule(this@Application))
        import(persistenceModule)
        import(domainModule)
        import(presentationModule)
    }
}