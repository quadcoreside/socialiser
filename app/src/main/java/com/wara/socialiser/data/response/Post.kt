package com.wara.socialiser.data.response

data class Post(
    val userId: Int,
    val id: Int,
    var title: String,
    val body: String,
)