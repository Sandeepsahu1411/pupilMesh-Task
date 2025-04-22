package com.example.pupilmeshtask.data_layer.api


import com.example.pupilmeshtask.data_layer.api.response.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService {

    @GET("manga/fetch")
    suspend fun getManga(
        @Query("page") page: Int,
        @Query("genres") genres: String = "Harem,Fantasy",
        @Query("nsfw") nsfw: Boolean = true,
        @Query("type") type: String = "all",
        @Header("X-RapidAPI-Key") apiKey: String = API_KEY,
        @Header("X-RapidAPI-Host") apiHost: String = HOST
    ): MangaResponse
}

