package com.example.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class PhotoDatabase (
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
)