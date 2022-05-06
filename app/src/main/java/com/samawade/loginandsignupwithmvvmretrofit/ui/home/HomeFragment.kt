package com.samawade.loginandsignupwithmvvmretrofit.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.samawade.loginandsignupwithmvvmretrofit.R
import com.samawade.loginandsignupwithmvvmretrofit.data.network.Resource
import com.samawade.loginandsignupwithmvvmretrofit.data.network.UserApi
import com.samawade.loginandsignupwithmvvmretrofit.data.repository.UserRepository
import com.samawade.loginandsignupwithmvvmretrofit.data.responses.User
import com.samawade.loginandsignupwithmvvmretrofit.databinding.FragmentHomeBinding
import com.samawade.loginandsignupwithmvvmretrofit.ui.base.BaseFragment
import com.samawade.loginandsignupwithmvvmretrofit.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it){
                is Resource.Success-> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading-> {
                    binding.progressbar.visible(true)
                }
            }
        })
        binding.buttonLogout.setOnClickListener { logout() }
    }

    private fun updateUI(user: User) {
        with(binding){
            textViewId.text = user.id.toString()
            textViewName.text = user.name
            textViewEmail.text = user.email
        }
    }

    override fun getViewModel()= HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }


}