package com.example.data.network.model

import com.example.domain.Photo
import com.example.domain.utils.DomainMapper

class PhotoApiMapper: DomainMapper<PhotoApi, Photo> {
    override fun mapToDomainModel(model: PhotoApi): Photo {
        return Photo(
            model.id,
            model.albumId,
            model.title,
            model.url,
            model.thumbnailUrl
        )
    }

    override fun mapFromDomainModel(domainModel: Photo): PhotoApi {
        return PhotoApi(
            domainModel.id,
            domainModel.albumId,
            domainModel.title,
            domainModel.url,
            domainModel.thumbnailUrl
        )
    }

    fun fromEntityList(initial: List<PhotoApi>): List<Photo>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntity(initial: Photo): PhotoApi {
        return mapFromDomainModel(initial)
    }
}