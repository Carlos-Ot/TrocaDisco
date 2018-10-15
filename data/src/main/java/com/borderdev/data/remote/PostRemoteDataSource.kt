package com.borderdev.data.remote

import com.borderdev.core.extensions.union
import com.borderdev.data.remote.network.ArticleApi
import com.borderdev.data.remote.network.entity.MainPage
import com.borderdev.data.remote.parser.PostParser
import com.borderdev.domain.datasource.PostRemoteDataSource
import com.borderdev.domain.model.Post
import io.reactivex.Observable

class PostRemoteDataSource(private val apiClient: ArticleApi) : PostRemoteDataSource {

    override fun getPosts(): Observable<List<Post>> {
        return apiClient.getMainContent()
                .map { mainPage: MainPage ->
                    val posts: List<Post> = emptyList()
                    posts.union(
                            mainPage.articles
                                    .map { PostParser.parse(it) },
                            mainPage.highLights
                                    .map { PostParser.parse(it) }
                    )
                }
    }

}