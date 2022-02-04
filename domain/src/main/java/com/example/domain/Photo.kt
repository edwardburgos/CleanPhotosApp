package com.example.domain

data class Photo (
    var id: Int,
    var albumId: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
)
