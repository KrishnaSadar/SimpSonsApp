package com.example.mlsc_task.network

import com.example.mlsc_task.models.chareCter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET("quotes?count=1000")
    suspend fun getCharacters(): Response<List<chareCter>>

    @GET("quotes")
    suspend fun getCharacter(
        @Query("character") character: String
    ): Response<List<chareCter>>
}
