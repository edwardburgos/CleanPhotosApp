package com.example.photosapp.overview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Photo
import com.example.photosapp.databinding.PhotoItemBinding
import com.example.photosapp.databinding.PhotoItemOptionalBinding

sealed class ViewHolders(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    class PhotoViewHolder(private val binding: PhotoItemBinding) : ViewHolders(binding){
        fun bin(photo: Photo){
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    class PhotoOptionalViewHolder(private val binding: PhotoItemOptionalBinding) : ViewHolders(binding){
        fun bin(photo: Photo){
            binding.photo = photo
            binding.executePendingBindings()
        }
    }
}