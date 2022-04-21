package com.example.lqqq.kotlincloud.coroutine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lqqq.kotlincloud.R
import com.example.lqqq.kotlincloud.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnFlowDownload.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_downloadFragment)
            }
            btnFlowRoom.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_roomFragment)
            }
        }
    }
}