package com.example.lqqq.kotlincloud

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val firstName = MutableLiveData<String>()
    private val secondName = MutableLiveData<String>()
    private val fullName = MediatorLiveData<String>()

    //    val fullName = Transformations.switchMap(firstName, Function{
//        return@Function MutableLiveData<String>().apply{
//            this.value = firstName.value.plus(secondName)
//        }
//    })
    init {
        fullName.addSource(firstName) {
            fullName.value = "$it${secondName.value}"
        }
        fullName.addSource(secondName, Observer {
            fullName.value = "${firstName.value}$it"
        })
    }
}