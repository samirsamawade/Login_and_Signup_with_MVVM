package com.samawade.loginandsignupwithmvvmretrofit.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.samawade.loginandsignupwithmvvmretrofit.databinding.FragmentLoginBinding
import com.samawade.loginandsignupwithmvvmretrofit.data.network.AuthApi
import com.samawade.loginandsignupwithmvvmretrofit.data.network.Resource
import com.samawade.loginandsignupwithmvvmretrofit.data.repository.AuthRepository
import com.samawade.loginandsignupwithmvvmretrofit.ui.base.BaseFragment
import com.samawade.loginandsignupwithmvvmretrofit.ui.enable
import com.samawade.loginandsignupwithmvvmretrofit.ui.handleApiError
import com.samawade.loginandsignupwithmvvmretrofit.ui.home.HomeActivity
import com.samawade.loginandsignupwithmvvmretrofit.ui.startNewActivity
import com.samawade.loginandsignupwithmvvmretrofit.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.user.access_token!!)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }

                }
                is Resource.Failure -> handleApiError(it){ login() }
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}