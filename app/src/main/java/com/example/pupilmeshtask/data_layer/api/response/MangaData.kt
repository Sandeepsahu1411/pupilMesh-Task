package com.example.pupilmeshtask.data_layer.api.response

import com.example.pupilmeshtask.data_layer.room_db.entity.Manga

data class MangaData(
    val authors: List<String>,
    val create_at: Long,
    val genres: List<String>,
    val id: String,
    val nsfw: Boolean,
    val status: String,
    val sub_title: String,
    val summary: String,
    val thumb: String,
    val title: String,
    val total_chapter: Int,
    val type: String,
    val update_at: Long
)
fun MangaData.toManga(): Manga {
    return Manga(
        id = this.id,
        title = this.title,
        thumb = this.thumb,
        type = this.type,
        summary = this.summary
    )
}