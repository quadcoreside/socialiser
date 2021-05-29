package com.wara.socialiser.data.response

data class Album(
    val userId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)