package com.example.photosapp.di

import com.example.photosapp.databinding.PhotoExtendedItemBinding
import com.example.photosapp.databinding.PhotoItemBinding
import com.example.photosapp.databinding.PhotoItemOptionalBinding
import com.example.photosapp.detail.DetailViewModel
import com.example.photosapp.detail.PhotosExtendedViewHolder
import com.example.photosapp.overview.OverviewViewModel
import com.example.photosapp.overview.ViewHolders
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { (binding: PhotoExtendedItemBinding) -> PhotosExtendedViewHolder(binding) }
    factory { (binding: PhotoItemBinding) -> ViewHolders.PhotoViewHolder(binding) }
    factory { (binding: PhotoItemOptionalBinding) -> ViewHolders.PhotoOptionalViewHolder(binding) }
    viewModel { OverviewViewModel(androidApplication(), get(), get()) }
    viewModel { DetailViewModel(androidApplication(), get()) }
}