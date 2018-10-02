package com.borderdev.data.source.remote.network

import com.borderdev.data.source.remote.network.entity.Feed
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface FeedApi {

    @GET("/category/podcast/feed")
    fun getPodcasts(): Observable<Feed>
}