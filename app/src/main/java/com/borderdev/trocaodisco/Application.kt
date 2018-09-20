package com.borderdev.trocaodisco

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class Application: Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
    }
}