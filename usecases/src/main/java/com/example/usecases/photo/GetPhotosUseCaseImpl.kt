package com.example.usecases.photo

import com.example.data.repository.PhotoRepository

class GetPhotosUseCaseImpl(private val photoRepository: PhotoRepository) : GetPhotosUseCase {
    override suspend operator fun invoke() = photoRepository.getPhotos()
}