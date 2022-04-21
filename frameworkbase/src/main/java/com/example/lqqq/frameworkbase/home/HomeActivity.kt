package com.example.lqqq.frameworkbase.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.lqqq.frameworkbase.databinding.ActivityHomeBinding
import kotlinx.coroutines.flow.collect

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory(HomeRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launchWhenCreated {
            viewModel.homeUiState.collect {
                binding.infoId.text = it.homeInfo.id.toString()
                binding.name.text = it.homeInfo.name
                binding.age.text = it.homeInfo.age.toString()
                binding.desc.text = it.homeInfo.desc
            }
        }
        binding.apply {
            search.setOnClickListener {
                viewModel.search()
            }
            update.setOnClickListener {
                viewModel.refreshUi()
            }
            save.setOnClickListener {
                val homeInfo = HomeInfo(name = binding.etName.text.toString(),
                    age = binding.etAge.text.toString().toInt(),
                    desc = binding.etDesc.text.toString())
                viewModel.save(homeInfo)
            }
        }
    }
}