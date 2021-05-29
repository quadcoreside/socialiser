package com.wara.socialiser.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wara.socialiser.R
import com.wara.socialiser.data.network.Resource
import com.wara.socialiser.data.network.UserApi
import com.wara.socialiser.data.repository.UserRepository
import com.wara.socialiser.data.response.User
import com.wara.socialiser.databinding.HomeFragmentBinding
import com.wara.socialiser.ui.base.BaseFragment
import com.wara.socialiser.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding, UserRepository>() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = MediaAdapater(listOf())
    private val layoutManager = LinearLayoutManager(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)


        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
            }
        })
        /*binding.buttonLogout.setOnClickListener {
            logout()
        }*/

        recyclerView = view.findViewById(R.id.media_recyclerview)
        recyclerView.apply {
            layoutManager = this@HomeFragment.layoutManager
            adapter = this@HomeFragment.adapter
        }
        val mediaList: ArrayList<String> = arrayListOf<String>().apply {
            add("Test 0")
            add("Test 1")
            add("Test 1")
            add("Test 1")
            add("Test 1")
            add("Test X")
        }

        adapter.updateList(mediaList)
    }


    private fun updateUI(user: User) {
        /*with(binding) {
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }*/
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = HomeFragmentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }
}