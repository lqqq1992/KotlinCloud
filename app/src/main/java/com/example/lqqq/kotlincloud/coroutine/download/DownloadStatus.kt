package com.example.lqqq.kotlincloud.coroutine.download

import java.io.File

// 密封类 成员都是它的子类
sealed class DownloadStatus{
    object None: DownloadStatus()
    data class Progress(val value:Int): DownloadStatus()
    data class Error(val throwable: Throwable): DownloadStatus()
    data class Done(val file:File): DownloadStatus()
}
