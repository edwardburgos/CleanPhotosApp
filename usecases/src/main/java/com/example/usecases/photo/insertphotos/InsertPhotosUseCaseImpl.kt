package com.example.usecases.photo.insertphotos

import com.example.data.repository.PhotoRepositoryImpl
import com.example.domain.Photo

class InsertPhotosUseCaseImpl(private val photoRepository: PhotoRepositoryImpl): InsertPhotosUseCase {
    override suspend operator fun invoke(photos: List<Photo>) = photoRepository.insertPhotos(photos)
}