package com.example.pupilmeshtask.data_layer.room_db.dto

import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity

data class MangaDto(
    val id: String,
    val title: String,
    val thumb: String,
    val summary: String,
    val type: String
) {
    fun toEntity() = MangaEntity(id, title, thumb, summary, type)
}
data class MangaResponse(
    val data: List<MangaDto>
)