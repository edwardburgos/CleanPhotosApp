package com.example.data.repository

import com.example.domain.Photo

interface PhotoRepository {
    suspend fun getPhotosFromApi(): List<Photo>
    fun getPhotosFromDatabase(): List<Photo>
    suspend fun getPhotos(): List<Photo>
    fun insertPhotos(photos: List<Photo>)
}