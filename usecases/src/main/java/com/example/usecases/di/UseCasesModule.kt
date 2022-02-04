package com.example.usecases.di

import com.example.usecases.photo.getphotos.GetPhotosDatabaseUseCaseImpl
import com.example.usecases.photo.getphotos.GetPhotosUseCaseImpl
import com.example.usecases.photo.insertphotos.InsertPhotosUseCaseImpl
import org.koin.dsl.module

val usecasesModule = module {
    factory { GetPhotosUseCaseImpl(get()) }
    factory { GetPhotosDatabaseUseCaseImpl(get()) }
    factory { InsertPhotosUseCaseImpl(get()) }
}