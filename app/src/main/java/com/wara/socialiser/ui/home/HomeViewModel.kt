package com.wara.socialiser.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wara.socialiser.data.network.Resource
import com.wara.socialiser.data.repository.JsonPlaceHolderRepo
import com.wara.socialiser.data.repository.UserRepository
import com.wara.socialiser.data.response.*
import com.wara.socialiser.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class HomeViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

    private val _posts: MutableLiveData<Resource<MutableList<Post>>> = MutableLiveData()
    val posts: LiveData<Resource<MutableList<Post>>>
        get() = _posts

    fun getPosts() = viewModelScope.launch {
        _posts.value = Resource.Loading
        _posts.value = repository.getPosts()
    }

    private val _post: MutableLiveData<Resource<MutableLiveData<Post>>> = MutableLiveData()
    val post: LiveData<Resource<MutableLiveData<Post>>>
        get() = _post

    fun getPost(id: Int) = viewModelScope.launch {
        _post.value = Resource.Loading
        _post.value = repository.getPost()
    }

    private val _postComments: MutableLiveData<Resource<MutableList<PostComment>>> = MutableLiveData()
    val postComments: LiveData<Resource<MutableList<PostComment>>>
        get() = _postComments

    fun getPostComments(post_id: Int) = viewModelScope.launch {
        _postComments.value = Resource.Loading
        _postComments.value = repository.getPostComments(post_id)
    }

    private val _albums: MutableLiveData<Resource<MutableList<Album>>> = MutableLiveData()
    val albums: LiveData<Resource<MutableList<Album>>>
        get() = _albums

    fun getAlbums() = viewModelScope.launch {
        _albums.value = Resource.Loading
        _albums.value = repository.getAlbums()
    }
}