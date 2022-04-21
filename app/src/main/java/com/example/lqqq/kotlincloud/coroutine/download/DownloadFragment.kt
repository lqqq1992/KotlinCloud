package com.example.lqqq.kotlincloud.coroutine.download

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.lqqq.kotlincloud.databinding.FragmentDownloadBinding
import kotlinx.coroutines.flow.collect
import java.io.File

class DownloadFragment : Fragment() {
    val URL = "http://bpic.588ku.com/element_origin_min_pic/16/10/29/2ac8e99273bc079e40a8dc079ca11b1f.jpg"
    private val binding: FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
    }
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
            context?.apply {
                DownloadManager.download(URL, File(getExternalFilesDir(null)?.path, "pic.jpg"))
                    .collect {status ->
                        when(status) {
                            is DownloadStatus.Progress ->{
                                binding.progressBar.progress = status.value
                            }
                            is DownloadStatus.Error -> {
                                Toast.makeText(context,"下载错误",Toast.LENGTH_SHORT).show()
                            }
                            is DownloadStatus.Done -> {
                                binding.progressBar.progress = 100
                                Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show()
                            }
                            is DownloadStatus.None -> {
                                Toast.makeText(context,"文件不存在",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            }
        }
    }
}