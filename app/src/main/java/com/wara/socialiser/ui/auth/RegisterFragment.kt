package com.wara.socialiser.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.wara.socialiser.MainActivity
import com.wara.socialiser.R
import com.wara.socialiser.data.network.AuthApi
import com.wara.socialiser.data.network.Resource
import com.wara.socialiser.data.repository.AuthRepository
import com.wara.socialiser.databinding.HomeFragmentBinding
import com.wara.socialiser.databinding.LoginFragmentBinding
import com.wara.socialiser.databinding.RegisterFragmentBinding
import com.wara.socialiser.ui.*
import com.wara.socialiser.ui.base.BaseFragment
import kotlinx.coroutines.launch
import java.util.*

class RegisterFragment : BaseFragment<AuthViewModel, RegisterFragmentBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonRegister.enable(false)

        binding.editTextEmail.addTextChangedListener {
            val email = binding.editTextEmail.text.toString().trim()
            binding.buttonRegister.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.editTextTextPasswordConfirmation.addTextChangedListener {
            val psw = binding.editTextEmail.text.toString().trim()
            val pswConfirm = binding.editTextEmail.text.toString().trim()

            if (psw != pswConfirm) {
                requireView().snackbar("Votre mot de passe ne correspond pas")
                binding.buttonRegister.enable(false)
            }
        }
        binding.textViewLoginNow.setOnClickListener {
            goToLogin()
        }
        binding.buttonRegister.setOnClickListener {
            val username = binding.editTextUsername.text.toString().trim().toLowerCase(Locale.ROOT)
            val email = binding.editTextEmail.text.toString().trim().toLowerCase(Locale.ROOT)

            val psw = binding.editTextPassword.text.toString().trim()
            val pswConfirm = binding.editTextTextPasswordConfirmation.text.toString().trim()

            /*  Une serie de verification des champs avant l'envoie */
            var ok = true;
            if (psw != pswConfirm) {
                requireView().snackbar("Votre mot de passe ne correspond pas")
                ok = false
            }
            if (username.contains(" ")){
                ok = false
            }

            if (ok) {
                register()
            }
        }
    }

    private fun register() {
        val username = binding.editTextUsername.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val psw = binding.editTextPassword.text.toString().trim()
        val pswConfirm = binding.editTextTextPasswordConfirmation.text.toString().trim()
        viewModel.register(username, email, psw)
    }
    private fun goToLogin() {

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): RegisterFragmentBinding = RegisterFragmentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)
}