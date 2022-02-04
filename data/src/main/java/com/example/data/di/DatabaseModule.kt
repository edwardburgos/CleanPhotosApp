package com.example.data.di

import com.example.data.database.PhotosDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { PhotosDatabase.getInstance(androidContext())}
    factory { (get() as PhotosDatabase).photoDao }
}