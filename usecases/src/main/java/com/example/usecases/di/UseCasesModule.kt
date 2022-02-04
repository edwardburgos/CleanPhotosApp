package com.example.usecases.di

import com.example.usecases.photo.GetPhotosUseCase
import com.example.usecases.photo.GetPhotosUseCaseImpl
import org.koin.dsl.module

val usecasesModule = module {
    factory { GetPhotosUseCaseImpl(get()) }
}