package com.borderdev.data.source.datasource.remote

import com.borderdev.data.source.local.database.entity.Post
import io.reactivex.Flowable
import io.reactivex.Observable

interface PostRemoteDataSource {

    fun getPosts(): Observable<List<Post>>
}