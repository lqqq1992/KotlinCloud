package com.example.lqqq.kotlincloud.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    // 指定主键，设置自动增长
    @PrimaryKey(autoGenerate = true) val id:Long,
    // 指定列名，类型
    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)var name:String,
    var age:Int,
)
