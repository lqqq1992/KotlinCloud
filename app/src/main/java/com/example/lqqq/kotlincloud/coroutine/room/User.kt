package com.example.lqqq.kotlincloud.coroutine.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    var firstName: String,
    var lastName: String,
    var age: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
