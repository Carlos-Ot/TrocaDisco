package com.borderdev.data.source.remote.network

import com.borderdev.data.source.remote.network.entity.MainPage
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ArticleApi {

    @GET("/")
    fun getMainContent(): Observable<MainPage>
}