package com.wara.socialiser.data.network

import androidx.lifecycle.MutableLiveData
import com.wara.socialiser.data.response.Album
import com.wara.socialiser.data.response.LoginResponse
import com.wara.socialiser.data.response.Post
import com.wara.socialiser.data.response.PostComment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse

    @POST("logout")
    suspend fun logout(): ResponseBody

    @GET("albums")
    suspend fun getAlbums() : MutableList<Album>

    @GET("posts")
    suspend fun getPosts() : MutableList<Post>

    @GET("posts/{id}")
    suspend fun getPost() : MutableLiveData<Post>

    @GET("posts/{id}/comments")
    suspend fun getPostComments(@Path("id") id: Int) : MutableList<PostComment>

    /*GET("users/{id}")
    suspend fun getUser() : MutableLiveData<User>*/
}