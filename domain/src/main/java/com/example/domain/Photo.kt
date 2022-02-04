package com.example.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "photo_table")
data class Photo (
    @PrimaryKey
    var id: Int,

    @ColumnInfo
    var albumId: Int,

    @ColumnInfo
    var title: String,

    @ColumnInfo
    var url: String,

    @ColumnInfo
    var thumbnailUrl: String
): Parcelable
