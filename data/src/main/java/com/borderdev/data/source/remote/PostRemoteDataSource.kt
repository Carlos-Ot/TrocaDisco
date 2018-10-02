package com.borderdev.data.source.remote

import com.borderdev.data.source.remote.parser.PostParser
import com.borderdev.data.source.datasource.remote.PostRemoteDataSource
import com.borderdev.data.source.local.database.entity.Post
import com.borderdev.data.source.remote.network.ArticleApi
import com.borderdev.data.source.remote.network.entity.MainPage
import io.reactivex.Observable

class PostRemoteDataSource(private val apiClient: ArticleApi) : PostRemoteDataSource {

    override fun getPosts(): Observable<List<Post>> {
        return apiClient.getMainContent()
                .map {mainPage: MainPage ->

                    val posts: MutableList<Post> = mutableListOf()

                    mainPage.articles.forEach {
                        val post = PostParser.parse(it)
                        posts.add(post)
                    }

                    mainPage.highLights.forEach {
                        val highlight = PostParser.parse(it)
                        posts.add(highlight)
                    }

                    posts.toList()
                }

    }

}