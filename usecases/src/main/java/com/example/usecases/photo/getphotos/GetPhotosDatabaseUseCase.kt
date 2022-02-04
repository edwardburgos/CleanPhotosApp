package com.example.usecases.photo.getphotos

import com.example.data.database.model.PhotoDatabase

interface GetPhotosDatabaseUseCase {
    suspend operator fun invoke(): List<PhotoDatabase>
}