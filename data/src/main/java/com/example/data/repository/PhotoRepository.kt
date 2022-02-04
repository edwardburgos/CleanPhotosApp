package com.example.data.repository

import com.example.data.database.model.PhotoDatabase
import com.example.data.network.model.PhotoApi
import com.example.domain.Photo

interface PhotoRepository {
    suspend fun getPhotosFromApi(): List<PhotoApi>
    fun getPhotosFromDatabase(): List<PhotoDatabase>
    fun insertPhotos(photos: List<Photo>)
}