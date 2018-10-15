package com.borderdev.presentation

import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<D>(): ViewModel(), LifecycleObserver {

    protected val disposables = CompositeDisposable()

    protected val state: MutableLiveData<ViewState<D>> = MutableLiveData()

    fun getState(): LiveData<ViewState<D>> {
        return state
    }

    abstract fun fetchData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchIfNeeded() {
        if (state.value == null) {
            fetchData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}