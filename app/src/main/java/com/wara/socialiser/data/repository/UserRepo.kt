package com.wara.socialiser.data.repository

import com.wara.socialiser.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

    suspend fun getPosts() = safeApiCall {
        api.getPosts()
    }

    suspend fun getPost() = safeApiCall {
        api.getPost()
    }

    suspend fun getPostComments() = safeApiCall {
        api.getPostComments()
    }

    suspend fun getAlbums() = safeApiCall {
        api.getAlbums()
    }

}