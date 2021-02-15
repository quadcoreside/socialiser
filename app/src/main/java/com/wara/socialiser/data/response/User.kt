package com.wara.socialiser.data.response

data class User(
    val api_token: String?,
    val created_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
)