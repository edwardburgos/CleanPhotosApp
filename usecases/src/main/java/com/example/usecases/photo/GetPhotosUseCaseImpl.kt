package com.example.usecases.photo

import com.example.data.repository.PhotoRepository
import com.example.data.repository.PhotoRepositoryImpl

class GetPhotosUseCaseImpl(private val photoRepository: PhotoRepositoryImpl): GetPhotosUseCase {
    override suspend operator fun invoke() = photoRepository.getPhotos()
}