package com.borderdev.trocaodisco.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.borderdev.presentation.common.BasePresenter
import com.borderdev.presentation.common.BaseView

import org.kodein.di.KodeinAware

abstract class BaseActivity<V: BaseView>: AppCompatActivity(), KodeinAware, BaseView {

    protected abstract val layoutId: Int
    protected abstract val presenter: BasePresenter<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        setPresenter()
        onCreate()

        presenter.subscribe()
    }

    protected abstract fun setPresenter()

    protected abstract fun onCreate()

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

}