package com.example.usecases.photo.getphotos

import com.example.data.network.model.PhotoApi

interface GetPhotosApiUseCase {
    suspend operator fun invoke(): List<PhotoApi>
}