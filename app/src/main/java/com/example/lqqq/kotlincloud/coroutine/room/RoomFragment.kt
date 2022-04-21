package com.example.lqqq.kotlincloud.coroutine.room

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lqqq.kotlincloud.databinding.FragmentRoomBinding
import kotlinx.coroutines.flow.collect

class RoomFragment : Fragment() {
    private val binding: FragmentRoomBinding by lazy {
        FragmentRoomBinding.inflate(layoutInflater)
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
        binding.apply {
            button.setOnClickListener {
                viewModel.insert(firstName.text.toString(),lastName.text.toString(),age.text.toString().toIntOrNull()?:0)
            }
        }
        context?.let {
            val adapter = UserAdapter(it)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(it)
            lifecycleScope.launchWhenCreated {
                viewModel.getAll().collect { value ->
                    adapter.setData(value)
                }
            }
        }
    }
}