package com.wara.socialiser.data.response

data class PostComment(
    val postId: Int,
    val id: Int,
    var name: String,
    val email: String,
    val body: String
)