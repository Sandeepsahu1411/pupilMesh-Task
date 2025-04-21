package com.example.pupilmeshtask.data_layer.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object ApiProvider {

    fun api() = Retrofit.Builder().baseUrl(BASE_URL).client(OkHttpClient.Builder().build())
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)

}