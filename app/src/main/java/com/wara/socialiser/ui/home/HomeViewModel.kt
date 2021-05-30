package com.wara.socialiser.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wara.socialiser.data.network.Resource
import com.wara.socialiser.data.repository.JsonPlaceHolderRepo
import com.wara.socialiser.data.repository.UserRepository
import com.wara.socialiser.data.response.AlbumResponse
import com.wara.socialiser.data.response.LoginResponse
import com.wara.socialiser.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository
) : BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
        get() = _user

    private val _albums: MutableLiveData<Resource<AlbumResponse>> = MutableLiveData()
    val albums: LiveData<Resource<AlbumResponse>>
        get() = _albums

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }

    fun getAlbums() = viewModelScope.launch {
        _albums.value = Resource.Loading
        //_albums.value = repository.getAlbum()
    }

}