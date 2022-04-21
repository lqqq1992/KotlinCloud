package com.example.lqqq.kotlincloud.coroutine.retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.lqqq.kotlincloud.coroutine.room.UserViewModel
import com.example.lqqq.kotlincloud.databinding.FragmentRetrofitBinding
import kotlinx.coroutines.flow.collect

class RetrofitFragment : Fragment() {
    private val binding: FragmentRetrofitBinding by lazy {
        FragmentRetrofitBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<UserViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            viewModel.getAllFlow(10000)
                .collect {
                    Log.d("---","$it")
                }
        }
    }
}