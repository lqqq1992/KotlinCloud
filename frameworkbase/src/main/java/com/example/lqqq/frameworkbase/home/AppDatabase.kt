package com.example.lqqq.frameworkbase.home

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [HomeInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getHomeInfoDao():HomeInfoDao

    companion object{
        @Volatile private var instance:AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            return instance?: synchronized(this){
                instance?:Room.databaseBuilder(context,AppDatabase::class.java,"frame-db")
                    .build().also {
                        instance = it
                    }
            }
        }
    }
}