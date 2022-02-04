package com.example.data.repository

import com.example.data.database.PhotoDao
import com.example.data.network.ApiService
import com.example.domain.Photo
import org.koin.core.component.KoinComponent
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class PhotoRepositoryImpl(
    val photoDao: PhotoDao,
    val apiService: ApiService

) : PhotoRepository, KoinComponent {

    override suspend fun getPhotosFromApi() = apiService.getPhotos(0, 100)
    override fun getPhotosFromDatabase() = photoDao.getAllPhotos()
    override fun insertPhotos(photos: List<Photo>) = photoDao.insertAll(photos)
    override suspend fun getPhotos(): List<Photo> {
        println("Dd")
        try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            println("fuck el estilo")
            return apiService.getPhotos(0, 100)

        } catch (e: IOException) {
            println("fuck el estilist")
            println(e.message)
            return photoDao.getAllPhotos()
        }
    }
}
