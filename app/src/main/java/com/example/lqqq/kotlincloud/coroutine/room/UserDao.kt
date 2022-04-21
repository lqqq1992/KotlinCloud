package com.example.lqqq.kotlincloud.coroutine.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // vararg 可变参数
    @Insert(onConflict = OnConflictStrategy.REPLACE)// id相同会替换
    suspend fun insertUser(vararg user: User)

    @Delete
    suspend fun deleteUser(vararg user: User)

    @Update
    suspend fun updateUser(vararg user: User)

    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: Int): Flow<List<User>>
}