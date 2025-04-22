package com.example.pupilmeshtask.data_layer.api.response

import com.example.pupilmeshtask.data_layer.room_db.entity.MangaEntity

data class MangaDto(
    val id: String,
    val title: String,
    val thumb: String,
    val summary: String,
    val type: String,
    val sub_title: String
) {
    fun toEntity() = MangaEntity(id, title, thumb, summary, type,sub_title)
}
data class MangaResponse(
    val data: List<MangaDto>
)