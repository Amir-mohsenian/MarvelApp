package com.example.marvelapp.data.source.remote

import com.example.marvelapp.model.BaseApiResponse
import com.example.marvelapp.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("apikey") apiKey: String,
                      @Query("hash") hash: String,
                      @Query("ts") timestamp: Long): Response<BaseApiResponse<Character>>
}