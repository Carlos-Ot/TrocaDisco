package com.borderdev.data.remote.network

import com.borderdev.data.remote.network.entity.Feed
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface FeedApi {

    @GET("/category/podcast/feed")
    fun getPodcasts(): Flowable<Feed>
}