package com.example.mad.data.api

import com.example.mad.data.model.Numbers
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {
    // The GET method needed to retrieve a random number trivia.
    @GET("/random/{numberType}?json")
    suspend fun getRandomNumber(@Path("numberType") numberType: String): Numbers
}