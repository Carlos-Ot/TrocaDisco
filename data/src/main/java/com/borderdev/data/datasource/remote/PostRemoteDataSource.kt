package com.borderdev.data.datasource.remote

import com.borderdev.data.local.database.entity.Post
import io.reactivex.Flowable
import io.reactivex.Observable

interface PostRemoteDataSource {

    fun getPosts(): Observable<List<Post>>
}