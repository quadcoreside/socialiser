package com.wara.socialiser.data.network

import com.wara.socialiser.data.response.AlbumResponse
import com.wara.socialiser.data.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaceholderAPI {

    @GET("https://jsonplaceholder.typicode.com/albums")
    suspend fun getAlbums(
    ) : AlbumResponse


}