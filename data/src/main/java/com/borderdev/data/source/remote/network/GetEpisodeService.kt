package com.borderdev.data.source.remote.network

import com.borderdev.core.BuildConfig
import com.prof.rssparser.Article
import com.prof.rssparser.Parser
import java.util.ArrayList

private const val podcastEndpoint = "category/podcast/feed"
class GetEpisodeService {
    private val episodesUrl = BuildConfig.BASE_URL + podcastEndpoint
    private val parser = Parser()

    fun getEpisodes() {

        parser.execute(episodesUrl)
        parser.onFinish(object : Parser.OnTaskCompleted {
            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTaskCompleted(list: ArrayList<Article>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}