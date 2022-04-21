package com.example.lqqq.kotlincloud.jetpack.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LocationObserver:LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createSomething(){}
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroySomething(){}
}