package com.example.usecases.photo.insertphotos

import com.example.domain.Photo

interface InsertPhotosUseCase {
    suspend operator fun invoke(photos: List<Photo>)
}