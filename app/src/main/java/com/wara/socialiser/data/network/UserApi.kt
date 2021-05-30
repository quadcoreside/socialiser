package com.wara.socialiser.data.network

import com.wara.socialiser.data.response.AlbumResponse
import com.wara.socialiser.data.response.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse

    @POST("logout")
    suspend fun logout(): ResponseBody

    @GET("albums")
    suspend fun getAlbums() : AlbumResponse
    fun getAlbum()
}