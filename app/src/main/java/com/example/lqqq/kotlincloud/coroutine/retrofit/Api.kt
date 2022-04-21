package com.example.lqqq.kotlincloud.coroutine.retrofit

import com.example.lqqq.kotlincloud.coroutine.room.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("something/{id}")
    suspend fun getUsers(
        @Path("id")id: Long
    ):List<User>
}