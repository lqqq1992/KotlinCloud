package com.example.lqqq.kotlincloud.jetpack.lifecycle

import androidx.lifecycle.LifecycleService

/**
 * 解耦service与组件
 */
class LocalService: LifecycleService() {
    fun constructor(){
        val observer = LocationObserver()
        lifecycle.addObserver(observer)
    }
}