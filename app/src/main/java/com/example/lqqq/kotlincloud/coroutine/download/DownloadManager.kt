package com.example.lqqq.kotlincloud.coroutine.download

import com.example.lqqq.kotlincloud.coroutine.copyTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException

// 单例
object DownloadManager {
    fun download(url: String, file: File): Flow<DownloadStatus> {
        return flow {
            val request = Request.Builder().url(url).get().build()
            val response = OkHttpClient.Builder().build().newCall(request).execute()
            if (response.isSuccessful) {
                response.body()!!.let { body -> // 为空抛出异常
                    val total = body.contentLength()
                    // 文件读写
                    file.outputStream().use { output ->
                        val input = body.byteStream()
                        var emittedProgress = 0L
                        input.copyTo(output) { bytesCopied ->
                            val progress = bytesCopied * 100 / total
                            if (progress - emittedProgress > 5) {
                                delay(100)
                                emit(DownloadStatus.Progress(progress.toInt()))
                                emittedProgress = progress
                            }
                        }
                    }
                }
                emit(DownloadStatus.Done(file))
            }else{
                throw IOException(response.toString())
            }
        }.catch { // 捕获上诉所有异常
            file.delete()
            emit(DownloadStatus.Error(it))
        }.flowOn(Dispatchers.IO)
    }
}