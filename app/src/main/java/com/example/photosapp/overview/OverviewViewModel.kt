package com.example.photosapp.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.domain.ApiStatus
import com.example.domain.Photo
import com.example.usecases.photo.getphotos.GetPhotosUseCaseImpl
import com.example.usecases.photo.insertphotos.InsertPhotosUseCaseImpl
import kotlinx.coroutines.*
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class OverviewViewModel(
    app: Application,
    private val getPhotos: GetPhotosUseCaseImpl,
    private val insertPhotos: InsertPhotosUseCaseImpl
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
                getPropertiesDeferred = async { getPhotos() }
            }
            try {
                if (showLoading) _status.value = ApiStatus.LOADING
                lateinit var listResult: List<Photo>
                withContext(Dispatchers.IO) {
                    listResult = getPropertiesDeferred.await()
                }
                _status.value = ApiStatus.DONE
                if (listResult.size > 0) {
                    _photos.value = listResult.sortedBy { it.id }.reversed()
                    withContext(Dispatchers.IO) {
                        try {
                            val socket = Socket()
                            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                            socket.close()
                            insertPhotos(listResult)
                        } catch (e: IOException) { }
                    }
                } else {
                    _status.value = ApiStatus.ERROR
                }
            } catch (t: Throwable) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun displayPropertyDetails(photo: Photo) {
        _navigateToSelectedPhoto.value = photo
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPhoto.value = null
    }
}
