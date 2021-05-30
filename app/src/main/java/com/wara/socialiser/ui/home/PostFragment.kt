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
import com.wara.socialiser.data.response.Album
import com.wara.socialiser.databinding.FragmentPostBinding
import com.wara.socialiser.ui.base.BaseFragment
import com.wara.socialiser.ui.post.PostCommentAdapater
import com.wara.socialiser.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class PostFragment : BaseFragment<HomeViewModel, FragmentPostBinding, UserRepository>() {
    private lateinit var recyclerView: RecyclerView

    private val adapter = PostCommentAdapater(listOf())
    private val layoutManager = LinearLayoutManager(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar2.visible(false)

        recyclerView = view.findViewById(R.id.media_recyclerview)
        recyclerView.apply {
            layoutManager = this@PostFragment.layoutManager
            adapter = this@PostFragment.adapter
        }

        /*val post_id = intent.getIntExtra("post_id");
        viewModel.getPost(post_id)*/

        /* Asyc tache non bloquant */
        viewModel.postComments.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressbar2.visible(false)
                    adapter.updateList(it.value)
                }
                is Resource.Loading -> {
                    binding.progressbar2.visible(true)
                }
            }
        })
    }

    private fun updateUI(albumList: ArrayList<Album>) {
        with(binding) {
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPostBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        /* Credientials */
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)

        return UserRepository(api)
    }
}