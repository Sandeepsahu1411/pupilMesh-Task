package com.example.pupilmeshtask.navigation

import kotlinx.serialization.Serializable

@Serializable
object LoginScreenRoute

@Serializable
object HomeScreenRoute

@Serializable
data class MangaDetailScreenRoute(
    val id: String = ""
)

@Serializable
object FaceDetectionScreenRoute