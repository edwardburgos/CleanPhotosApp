package com.example.data.repository

import com.example.data.database.PhotoDao
import com.example.data.network.ApiService
import com.example.domain.Photo
import kotlinx.coroutines.Deferred
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class PhotoRepositoryImpl(
    val photoDao: PhotoDao,
    val apiService: ApiService

): PhotoRepository, KoinComponent {

    override suspend fun getPhotosFromApi() = apiService.getPhotos(0, 100)
    override fun getPhotosFromDatabase() = photoDao.getAllPhotos()
    override fun insertPhotos(photos: List<Photo>) = photoDao.insertAll(photos)
//    fun xx(): Deferred<List<Photo>> {
//        try {
//            val cardi = apiService.getPhotos(0, 100)
//            val car = getPhotosFromApi()
//            println(car.)
//
//            println("fff")
//            return apiService.getPhotos(0, 100)
//        } catch (t: Throwable) {
//            return apiService.getPhotos(0, 100)
//            // return photoDao.getAllPhotos()
//        }
//    }

//    fun xx(): Deferred<List<Photo>> {
//        try {
//            val cardi = apiService.getPhotos(0, 100)
//            val car = getPhotosFromApi()
//            println(car.)
//
//            println("fff")
//            return apiService.getPhotos(0, 100)
//        } catch (t: Throwable) {
//            return apiService.getPhotos(0, 100)
//           // return photoDao.getAllPhotos()
//        }
//    }

 //   override fun getPhotos() = apiService.getPhotos(0, 100)


//        fun hola() {
//    run {
//
//    }

    override suspend fun getPhotos(): List<Photo> {
        println("Dd")
        try {

            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            println("fuck el estilo")
            return apiService.getPhotos(0, 100)

        } catch(e: IOException) {
            println("fuck el estilist")

            return photoDao.getAllPhotos()
        }

        //apiService.getPhotos(0, 100)
        //    override fun getPhotosFromDatabase() = photoDao.getAllPhotos()

//        if (connectivity.hasNetworkAccess()) {
//            println("FUCK EL ESTILO")
//        } else {
//            println("FUCK EL ESTILIST")
//            //  photoDao.getAllPhotos()
//        }
    }
}


//        photoDao.getAllPhotos()
//        viewModelScope.launch {
//            lateinit var getPropertiesDeferred: Deferred<List<Photo>>
//            withContext(Dispatchers.IO) {
//                getPropertiesDeferred = mainRepository.getPhotosFromApi()
//            }
//            try {
//                if (showLoading) _status.value = ApiStatus.LOADING
//                lateinit var listResult: List<Photo>
//                withContext(Dispatchers.IO) {
//                    listResult = getPropertiesDeferred.await()
//                }
//                _status.value = ApiStatus.DONE
//                if (listResult.size > 0) {
//                    _photos.value = listResult.sortedBy { it.id }.reversed()
//                    withContext(Dispatchers.IO) {
//                        mainRepository.insertPhotos(listResult)
//                    }
//                }
//            } catch (t: Throwable) {
//                lateinit var databasePhotos: List<Photo>
//                withContext(Dispatchers.IO) {
//                    databasePhotos = mainRepository.getPhotosFromDatabase()
//                }
//                if (t.message == "Unable to resolve host \"jsonplaceholder.typicode.com\": No address associated with hostname" && databasePhotos.size != 0) {
//                    _photos.value = databasePhotos
//                    _status.value = ApiStatus.DONE
//                } else {
//                    _status.value = ApiStatus.ERROR
//                }
//
//            }
  //      }
   // }
