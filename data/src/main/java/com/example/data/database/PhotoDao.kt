package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.PhotoDatabase

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photos: List<PhotoDatabase>)

    @Query("SELECT * FROM photo_table ORDER BY id DESC")
    fun getAllPhotos(): List<PhotoDatabase>
}
