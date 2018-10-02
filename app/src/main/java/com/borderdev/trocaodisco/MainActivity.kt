package com.borderdev.trocaodisco

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.borderdev.data.source.remote.network.ServiceClient
import com.borderdev.data.source.remote.network.entity.Feed
import com.borderdev.data.source.remote.network.entity.MainPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}