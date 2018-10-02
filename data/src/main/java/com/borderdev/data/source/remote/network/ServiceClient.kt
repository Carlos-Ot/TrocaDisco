package com.borderdev.data.source.remote.network

import com.borderdev.core.BuildConfig
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tickaroo.tikxml.TikXml



class ServiceClient {

    companion object {
        @JvmStatic
        fun getFeedClient(): FeedApi {

            val tikXml = TikXml.Builder()
                    .exceptionOnUnreadXml(false)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(TikXmlConverterFactory.create(tikXml))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient())
                    .build()

            return retrofit.create(FeedApi::class.java)
        }

        @JvmStatic
        fun getArticleClient(): ArticleApi {

            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(JspoonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpClient())
                    .build()

            return retrofit.create(ArticleApi::class.java)
        }
    }
}