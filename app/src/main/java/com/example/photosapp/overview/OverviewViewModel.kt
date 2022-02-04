package com.example.photosapp.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.data.database.model.PhotoDatabase
import com.example.data.database.model.PhotoDatabaseMapper
import com.example.data.network.model.PhotoApi
import com.example.data.network.model.PhotoApiMapper
import com.example.domain.ApiStatus
import com.example.domain.Photo
import com.example.usecases.photo.getphotos.GetPhotosApiUseCaseImpl
import com.example.usecases.photo.getphotos.GetPhotosDatabaseUseCaseImpl
import com.example.usecases.photo.insertphotos.InsertPhotosUseCaseImpl
import kotlinx.coroutines.*
import java.net.InetSocketAddress
import java.net.Socket

class OverviewViewModel(
    app: Application,
    private val getPhotosApi: GetPhotosApiUseCaseImpl,
    private val getPhotosDatabase: GetPhotosDatabaseUseCaseImpl,
    private val insertPhotos: InsertPhotosUseCaseImpl,
    private val apiMapper: PhotoApiMapper,
    private val databaseMapper: PhotoDatabaseMapper
) : AndroidViewModel(app) {

    private val _status = MutableLiveData<ApiStatus>()
    val status: MutableLiveData<ApiStatus>
        get() = _status

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private val _navigateToSelectedPhoto = MutableLiveData<PhotoApi>()
    val navigateToSelectedPhoto: LiveData<PhotoApi>
        get() = _navigateToSelectedPhoto

    init {
        getPhotosOverview(true)
    }

    fun getPhotosOverview(showLoading: Boolean) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val socket = Socket()
                    socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                    socket.close()
                }
                lateinit var getPropertiesDeferred: Deferred<List<PhotoApi>>
                lateinit var listResult: List<PhotoApi>
                if (showLoading) _status.value = ApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    getPropertiesDeferred = async { getPhotosApi() }
                    listResult = getPropertiesDeferred.await()

                }
                val convertedListResult = apiMapper.fromEntityList(listResult)
                _status.value = ApiStatus.DONE
                if (listResult.size > 0) {
                    _photos.value = convertedListResult.sortedBy { it.id }.reversed()
                    withContext(Dispatchers.IO) {
                            insertPhotos(convertedListResult)
                    }
                } else {
                    _status.value = ApiStatus.ERROR
                }
            } catch (t: Throwable) {
                lateinit var getPropertiesDeferred: Deferred<List<PhotoDatabase>>
                lateinit var listResult: List<PhotoDatabase>
                if (showLoading) _status.value = ApiStatus.LOADING
                withContext(Dispatchers.IO) {
                    getPropertiesDeferred = async { getPhotosDatabase() }
                    listResult = getPropertiesDeferred.await()

                }
                val convertedListResult = databaseMapper.fromEntityList(listResult)
                _status.value = ApiStatus.DONE
                if (listResult.size > 0) {
                    _photos.value = convertedListResult
                } else {
                    _status.value = ApiStatus.ERROR
                }
            }
        }
    }

    fun displayPropertyDetails(photo: Photo) {
        _navigateToSelectedPhoto.value = apiMapper.toEntity(photo)
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPhoto.value = null
    }
}
