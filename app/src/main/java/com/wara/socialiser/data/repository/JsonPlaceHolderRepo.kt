package com.wara.socialiser.data.repository

import com.wara.socialiser.data.network.PlaceholderAPI

class JsonPlaceHolderRepo(
    private val api: PlaceholderAPI
) : BaseRepository() {


    suspend fun getAlbum() = safeApiCall {
        api.getAlbum()
    }

}