package com.example.lqqq.kotlincloud.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lqqq.kotlincloud.databinding.ActivityFlowBinding

class FlowActivity : AppCompatActivity() {
    private val binding: ActivityFlowBinding by lazy {
        ActivityFlowBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}