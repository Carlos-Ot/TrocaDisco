package com.borderdev.domain.datasource

import com.borderdev.domain.model.Post
import io.reactivex.Observable

interface PostRemoteDataSource {
    fun getPosts(): Observable<List<Post>>
}