package com.wara.socialiser.data.repository

import com.wara.socialiser.data.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}