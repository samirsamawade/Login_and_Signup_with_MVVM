package com.samawade.loginandsignupwithmvvmretrofit.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.samawade.loginandsignupwithmvvmretrofit.R
import com.samawade.loginandsignupwithmvvmretrofit.databinding.FragmentLoginBinding
import com.samawade.loginandsignupwithmvvmretrofit.network.AuthApi
import com.samawade.loginandsignupwithmvvmretrofit.network.Resource
import com.samawade.loginandsignupwithmvvmretrofit.repository.AuthRepository
import com.samawade.loginandsignupwithmvvmretrofit.ui.base.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                    Log.d("TAG", it.toString())
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_LONG).show()
                    Log.d("TAG", it.toString())
                }
            }
        })

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            //add input validations
            viewModel.login(email,password)
        }
    }

    override fun getViewModel()= AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}