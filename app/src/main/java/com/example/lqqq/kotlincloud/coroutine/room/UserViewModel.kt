package com.example.lqqq.kotlincloud.coroutine.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lqqq.kotlincloud.coroutine.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val users = MutableLiveData<List<User>>()
    fun insert(firstName: String, lastName: String, age: Int) {
        viewModelScope.launch {
            AppDatabase.getInstance(getApplication())
                .userDao()
                .insertUser(User(firstName = firstName, lastName = lastName, age = age))
        }
    }

    fun getAll(): Flow<List<User>> {
        return AppDatabase.getInstance(getApplication()).userDao().getAllUser()
            .catch { e -> e.printStackTrace() }
            .flowOn(Dispatchers.IO)
    }

    fun getAllFromNet(id: Long) = viewModelScope.launch {
        flow {
            val list = RetrofitClient.api.getUsers(id)
            emit(list)
        }.flowOn(Dispatchers.IO)
            .catch { e -> e.printStackTrace() }
            .collect {
                users.value = it
            }
    }

    suspend fun getAllFlow(id: Long) = flow {
        val list = RetrofitClient.api.getUsers(id)
        emit(list)
    }.flowOn(Dispatchers.IO)
        .catch { e -> e.printStackTrace() }
}