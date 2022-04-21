package com.example.lqqq.frameworkbase.home


class HomeRepository {
    private val infoDao = FrameApp.getInstance().getDatabase().getHomeInfoDao()
    suspend fun getHomeInfo():HomeInfo{
        return infoDao.getAll()[0]
    }
    suspend fun setHomeInfo(homeInfo: HomeInfo){
        infoDao.insertInfo(homeInfo)
    }
    suspend fun updateInfo(info: HomeInfo){
        infoDao.updateInfo(info)
    }
    suspend fun updateFromRemote(){

    }
}