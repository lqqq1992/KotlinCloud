package com.example.lqqq.frameworkbase.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _homeUiState: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState(HomeInfo("张三", 18, "法外狂徒"),true))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()
    private var refreshJob: Job? = null
    fun refreshUi() {
        refreshJob?.cancel()
        refreshJob = viewModelScope.launch {
            val homeInfo = homeRepository.getHomeInfo()
            _homeUiState.update {
                // copy函数更新部分数据
                it.copy(homeInfo = homeInfo)
            }
        }
    }

    fun save(homeInfo: HomeInfo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                homeRepository.setHomeInfo(homeInfo)
            }
        }
    }

    fun search() {
        viewModelScope.launch {
            val homeInfo = homeRepository.getHomeInfo()
            _homeUiState.update {
                // copy函数更新部分数据
                it.copy(homeInfo = homeInfo)
            }
        }
    }

    fun update(info: HomeInfo) {
        viewModelScope.launch {
            launch {
                homeRepository.updateInfo(info)
            }.join()
            search()
        }
    }
}