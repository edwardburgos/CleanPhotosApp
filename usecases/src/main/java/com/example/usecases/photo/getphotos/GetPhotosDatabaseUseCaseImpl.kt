package com.example.usecases.photo.getphotos

import com.example.data.repository.PhotoRepositoryImpl

class GetPhotosDatabaseUseCaseImpl(private val photoRepository: PhotoRepositoryImpl):
    GetPhotosDatabaseUseCase {
    override suspend operator fun invoke() = photoRepository.getPhotosFromDatabase()
}