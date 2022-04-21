package com.example.lqqq.kotlincloud.jetpack.lifecycle

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * lifecycle 解耦页面和组件
 */
class MyChronometer(context: Context,attrs: AttributeSet): Chronometer(context,attrs),LifecycleObserver {
    private var elapsedTime: Long = 0

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startMeter(){
        base = SystemClock.elapsedRealtime()-elapsedTime
        start()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopMeter(){
        elapsedTime = SystemClock.elapsedRealtime()-base
        stop()
    }
}