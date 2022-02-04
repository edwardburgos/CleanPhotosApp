package com.example.usecases.di

import com.example.usecases.photo.getphotos.GetPhotosDatabaseUseCaseImpl
import com.example.usecases.photo.getphotos.GetPhotosApiUseCaseImpl
import com.example.usecases.photo.insertphotos.InsertPhotosUseCaseImpl
import org.koin.dsl.module

val usecasesModule = module {
    factory { GetPhotosApiUseCaseImpl(get()) }
    factory { GetPhotosDatabaseUseCaseImpl(get()) }
    factory { InsertPhotosUseCaseImpl(get()) }
}