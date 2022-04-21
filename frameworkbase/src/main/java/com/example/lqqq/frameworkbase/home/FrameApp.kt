package com.example.lqqq.frameworkbase.home

import android.app.Application

class FrameApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object{
//        private var instance: FrameApp? = null
//        fun getInstance() = instance!!
        //情况二：声明延迟初始化属性
        private lateinit var instance: FrameApp
        fun getInstance() = instance
    }
    fun getDatabase():AppDatabase{
        return AppDatabase.getInstance(this)
    }
}