package com.example.data.di

import com.example.data.repository.PhotoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { PhotoRepositoryImpl(get(), get()) }
}