package com.example.pupilmeshtask.data_layer.room_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "manga_table")
data class MangaEntity(
    @PrimaryKey val id: String,
    val title: String,
    val thumb: String,
    val summary: String,
    val type: String,
    val sub_title: String
)

