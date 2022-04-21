package com.example.lqqq.kotlincloud.jetpack.room

import android.content.Context
import androidx.room.Room

class PersonRepository(context: Context) {
    val kotlinDatabase = Room.databaseBuilder(context,KotlinDatabase::class.java,"kotlin-db")
}