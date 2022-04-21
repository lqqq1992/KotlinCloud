package com.example.lqqq.kotlincloud.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lqqq.kotlincloud.R
import com.example.lqqq.kotlincloud.jetpack.lifecycle.MyChronometer

class JetpackActivity : AppCompatActivity() {
    private val chronometer: MyChronometer
        get() = findViewById(R.id.chronometer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)
        // lifecycle 解耦页面和组件
        lifecycle.addObserver(chronometer)
//        Room.databaseBuilder(this,KotlinDatabase::class.java,"kotlin-db").build().userDao()
    }
}