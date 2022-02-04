package com.example.photosapp.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.Photo
import com.example.usecases.photo.GetPhotosUseCase
import com.example.usecases.photo.GetPhotosUseCaseImpl
import kotlinx.coroutines.*

enum class ApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(
    app: Application,
    private val getPhotos: GetPhotosUseCaseImpl
    //,
    //val getPhotos: GetPhotosUseCase
) : AndroidViewModel(app) {

    private val _status = MutableLiveData<ApiStatus>()
    val status: MutableLiveData<ApiStatus>
        get() = _status

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _navigateToSelectedPhoto = MutableLiveData<Photo>()
    val navigateToSelectedPhoto: LiveData<Photo>
        get() = _navigateToSelectedPhoto

    init {
        getPhotosOverview(true)


    }

    fun getPhotosOverview(showLoading: Boolean) {
        viewModelScope.launch {

            lateinit var getPropertiesDeferred: Deferred<List<Photo>>
            withContext(Dispatchers.IO) {
                //val rosa = photoRepository.getPhotos()()
                //(rosa())
                println("MAMA")
               // photoRepository.hola()
                getPropertiesDeferred = async {
                    //photoRepository.getPhotosFromApi()
                    //
                // photoRepository.getPhotos()

                    getPhotos()
                }
                //photoRepository.getPhotos()()

            }
            if (showLoading) _status.value = ApiStatus.LOADING
            lateinit var listResult: List<Photo>
                withContext(Dispatchers.IO) {
                    listResult = getPropertiesDeferred.await()
                }
            _status.value = ApiStatus.DONE
            if (listResult.size > 0) {
                    _photos.value = listResult.sortedBy { it.id }.reversed()
                println("qwwwwwwwwwwwwww")
//                    withContext(Dispatchers.IO) {
//                        photoRepository.insertPhotos(listResult)
//                    }
                }


//            try {
//                if (showLoading) _status.value = ApiStatus.LOADING
//                lateinit var listResult: List<Photo>
//                withContext(Dispatchers.IO) {
//                    listResult = getPropertiesDeferred.await()
//                }
//                _status.value = ApiStatus.DONE
//                if (listResult.size > 0) {
//                    println("ff55")
//                    _photos.value = listResult.sortedBy { it.id }.reversed()
//                    withContext(Dispatchers.IO) {
//                        photoRepository.insertPhotos(listResult)
//                    }
//                }
//            } catch (t: Throwable) {
//                println("qlq")
//                lateinit var databasePhotos: List<Photo>
//                withContext(Dispatchers.IO) {
//                    databasePhotos = photoRepository.getPhotosFromDatabase()
//                }
//                if (databasePhotos.size != 0) {
//                    _photos.value = databasePhotos
//                    _status.value = ApiStatus.DONE
//                } else {
//                    _status.value = ApiStatus.ERROR
//                }
//            }
        }
    }

    fun displayPropertyDetails(photo: Photo) {
        _navigateToSelectedPhoto.value = photo
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPhoto.value = null
    }
}
