package com.example.lqqq.kotlincloud.jetpack.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class],version = 1,exportSchema = false)
abstract class KotlinDatabase: RoomDatabase() {
    abstract fun personDao():PersonDao
}