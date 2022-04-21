package com.example.lqqq.frameworkbase.home

import androidx.room.*

@Dao
interface HomeInfoDao {
    @Query("SELECT * FROM HomeInfo")
    suspend fun getAll():List<HomeInfo>
    @Insert
    suspend fun insertAll(vararg info:HomeInfo)
    @Insert
    suspend fun insertInfo(info: HomeInfo)
    @Insert
    suspend fun insertAll(list: List<HomeInfo>)
    @Update
    suspend fun updateInfo(info: HomeInfo)
    @Delete
    suspend fun deleteInfo(info: HomeInfo)
    @Query("DELETE FROM HomeInfo WHERE id = :id")
    suspend fun deleteById(id:Long)
}