package com.example.usecases.photo

import com.example.domain.Photo

interface GetPhotosUseCase {
    suspend operator fun invoke(): List<Photo>
}