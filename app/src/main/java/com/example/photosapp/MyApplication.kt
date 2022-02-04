package com.example.photosapp

import android.app.Application
import com.example.data.di.apiModule
import com.example.data.di.databaseModule
import com.example.data.di.repositoryModule
import com.example.photosapp.di.presentationModule
import com.example.usecases.di.usecasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(presentationModule, usecasesModule) + dataModules)
        }

    }
}

val dataModules = listOf(apiModule, databaseModule, repositoryModule)