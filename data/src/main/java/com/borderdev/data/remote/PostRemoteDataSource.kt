package com.borderdev.data.remote

import com.borderdev.data.remote.parser.PostParser
import com.borderdev.data.datasource.remote.PostRemoteDataSource
import com.borderdev.data.local.database.entity.Post
import com.borderdev.data.remote.network.ArticleApi
import com.borderdev.data.remote.network.entity.MainPage
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