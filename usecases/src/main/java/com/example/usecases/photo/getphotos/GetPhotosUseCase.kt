package com.example.usecases.photo.getphotos

import com.example.domain.Photo

interface GetPhotosUseCase {
    suspend operator fun invoke(): List<Photo>
}