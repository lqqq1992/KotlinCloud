package com.example.lqqq.kotlincloud.jetpack.lifecycle

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        // 给整个应用程序添加，Lifecycle.Event.ON_CREATE只会调用一次，
        // Lifecycle.Event.ON_DESTROY不会调用
        ProcessLifecycleOwner.get().lifecycle.addObserver(LocationObserver())
    }
}