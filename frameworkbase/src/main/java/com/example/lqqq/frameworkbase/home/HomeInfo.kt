package com.example.lqqq.frameworkbase.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HomeInfo")
data class HomeInfo(var name:String,var age:Int,var desc:String){
    @PrimaryKey(autoGenerate = true)
    var id:Long? = null
}
