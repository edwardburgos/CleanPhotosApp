package com.example.data.database.model

import com.example.domain.Photo
import com.example.domain.utils.DomainMapper

class PhotoDatabaseMapper: DomainMapper<PhotoDatabase, Photo> {
    override fun mapToDomainModel(model: PhotoDatabase): Photo {
        return Photo(
            model.id,
            model.albumId,
            model.title,
            model.url,
            model.thumbnailUrl
        )
    }

    override fun mapFromDomainModel(domainModel: Photo): PhotoDatabase {
        return PhotoDatabase(
            domainModel.id,
            domainModel.albumId,
            domainModel.title,
            domainModel.url,
            domainModel.thumbnailUrl
        )
    }

    fun fromEntityList(initial: List<PhotoDatabase>): List<Photo>{
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Photo>): List<PhotoDatabase>{
        return initial.map { mapFromDomainModel(it) }
    }
}