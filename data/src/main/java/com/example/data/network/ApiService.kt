package com.example.data.network

import com.example.domain.Photo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos")
    suspend fun getPhotos(@Query("_start") startAt: Int, @Query("_limit") endAT: Int):
            List<Photo>
}