package com.example.photosapp.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.Photo
import com.example.photosapp.overview.ApiStatus
import com.example.usecases.photo.getphotos.GetPhotosDatabaseUseCaseImpl
import kotlinx.coroutines.*

class DetailViewModel(app: Application, private val getPhotosDatabase: GetPhotosDatabaseUseCaseImpl) : //val photoRepository: PhotoRepositoryImpl
    AndroidViewModel(app) {

    var currentPhotoPosition = 0

    private val _status = MutableLiveData<ApiStatus>()
    val status: MutableLiveData<ApiStatus>
        get() = _status

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    init {
        getPhotosDetail()
    }

    fun getPhotosDetail() {
        viewModelScope.launch {
            lateinit var getPropertiesDeferred: Deferred<List<Photo>>
            withContext(Dispatchers.IO) {
                getPropertiesDeferred = async { getPhotosDatabase() }
            }
            try {
                _status.value = ApiStatus.LOADING
                lateinit var listResult: List<Photo>
                withContext(Dispatchers.IO) {
                    listResult = getPropertiesDeferred.await()
                }
                _status.value = ApiStatus.DONE
                if (listResult.size > 0) {
                    //println(listResult.sortedBy { it.id }) //.reversed()
                    _photos.value = listResult
                        //.sortedBy { it.id }.reversed()
                }
            } catch (t: Throwable) {
                _status.value = ApiStatus.ERROR

            }
        }
    }
}