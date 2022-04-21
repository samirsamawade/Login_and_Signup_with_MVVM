package com.samawade.loginandsignupwithmvvmretrofit.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.samawade.loginandsignupwithmvvmretrofit.R
import com.samawade.loginandsignupwithmvvmretrofit.databinding.FragmentLoginBinding
import com.samawade.loginandsignupwithmvvmretrofit.network.AuthApi
import com.samawade.loginandsignupwithmvvmretrofit.repository.AuthRepository
import com.samawade.loginandsignupwithmvvmretrofit.ui.base.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun getViewModel()= AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}