package com.example.usecases.photo.getphotos

import com.example.data.repository.PhotoRepositoryImpl

class GetPhotosUseCaseImpl(private val photoRepository: PhotoRepositoryImpl): GetPhotosUseCase {
    override suspend operator fun invoke() = photoRepository.getPhotos()
}