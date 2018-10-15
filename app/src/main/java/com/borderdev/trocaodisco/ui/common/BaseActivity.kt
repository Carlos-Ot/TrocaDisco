package com.borderdev.trocaodisco.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.borderdev.presentation.BaseViewModel
import com.borderdev.presentation.ViewState
import org.kodein.di.KodeinAware

abstract class BaseActivity<D>: AppCompatActivity(), KodeinAware {

    protected abstract val layoutId: Int
    protected abstract val viewModel: BaseViewModel<D>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        lifecycle.addObserver(viewModel)
        observe()

    }

    protected abstract fun onCreate()

    protected abstract fun observe()

    protected abstract fun progressIndicator(visible: Boolean)

    protected abstract fun handleSuccess(data: D)

    protected abstract fun handleError(error: Throwable)

    protected fun handleState(state: ViewState<D>) {
        when (state.status) {
            ViewState.Status.LOADING -> {
                progressIndicator(true)
            }
            ViewState.Status.SUCCESS -> {
                state.data?.let {
                    handleSuccess(it)
                }
            }
            ViewState.Status.ERROR -> {
                state.error?.let {
                    handleError(it)
                }
            }
        }
    }

}