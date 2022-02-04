package com.example.usecases.photo.getphotos

import com.example.data.repository.PhotoRepositoryImpl

class GetPhotosApiUseCaseImpl(private val photoRepository: PhotoRepositoryImpl): GetPhotosApiUseCase {
    override suspend operator fun invoke() = photoRepository.getPhotosFromApi()
}