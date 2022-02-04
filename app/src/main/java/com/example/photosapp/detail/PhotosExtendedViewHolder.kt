package com.example.photosapp.detail

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Photo
import com.example.photosapp.databinding.PhotoExtendedItemBinding

class PhotosExtendedViewHolder(private val binding: PhotoExtendedItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bin(photo: Photo) {
        binding.photo = photo
        binding.executePendingBindings()
    }
}