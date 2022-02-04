package com.example.data.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoApi (
    var id: Int,
    var albumId: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
): Parcelable