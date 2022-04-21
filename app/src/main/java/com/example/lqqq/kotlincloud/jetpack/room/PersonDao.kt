package com.example.lqqq.kotlincloud.jetpack.room

import androidx.room.*

@Dao
interface PersonDao {
    // vararg 可变参数
    @Insert
    fun insertUser(vararg person: Person)
    @Delete
    fun deleteUser(vararg person: Person)
    @Update
    fun updateUser(vararg person: Person)
    @Query("SELECT * FROM person")
    fun getAllUser():List<Person>
    @Query("SELECT * FROM person WHERE id = :id")
    fun getUserById(id:Int):List<Person>
}