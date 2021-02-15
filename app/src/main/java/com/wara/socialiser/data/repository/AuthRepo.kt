package com.wara.socialiser.data.repository

import com.wara.socialiser.data.UserPreferences
import com.wara.socialiser.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(){

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }

}