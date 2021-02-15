package com.wara.socialiser.ui.base

import androidx.lifecycle.ViewModel
import com.wara.socialiser.data.network.UserApi
import com.wara.socialiser.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }

}