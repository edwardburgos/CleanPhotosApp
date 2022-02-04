package com.example.photosapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.Photo
import com.example.photosapp.R
import com.example.photosapp.databinding.PhotoItemBinding
import com.example.photosapp.databinding.PhotoItemOptionalBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

import java.lang.IllegalArgumentException

class PhotosAdapter(val onClickListener: OnClickListener): ListAdapter<Photo, ViewHolders>(DiffCallback), KoinComponent {

    companion object DiffCallback: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolders {
        val viewHolder: ViewHolders.PhotoViewHolder by inject {
            parametersOf(PhotoItemBinding.inflate(LayoutInflater.from(parent.context)))
        }
        val viewHolderOptional: ViewHolders.PhotoOptionalViewHolder by inject {
            parametersOf(PhotoItemOptionalBinding.inflate(LayoutInflater.from(parent.context)))
        }
        return when (viewType) {
            R.layout.photo_item ->
                return viewHolder
            R.layout.photo_item_optional ->
                return viewHolderOptional
            else -> throw IllegalArgumentException("Invalid viewType provided")
        }
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        when (holder) {
            is ViewHolders.PhotoViewHolder -> holder.bin(item)
            is ViewHolders.PhotoOptionalViewHolder -> holder.bin(item)
        }
    }

    class OnClickListener(val clickListener: (photo: Photo) -> Unit) {
        fun onClick(photo: Photo) = clickListener(photo)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) R.layout.photo_item else R.layout.photo_item_optional
    }
}