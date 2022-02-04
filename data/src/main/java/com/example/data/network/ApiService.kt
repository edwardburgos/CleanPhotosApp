package com.example.data.network

import com.example.data.network.model.PhotoApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(@Query("_start") startAt: Int, @Query("_limit") endAT: Int):
            List<PhotoApi>
}