package com.example.core.data.source.local

import com.example.core.data.source.local.entity.GithubEntity
import com.example.core.data.source.local.room.GithubDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val githubDao: GithubDao) {


    fun getAllGithub(): Flow<List<GithubEntity>> = githubDao.getAllGithub()
//
//    fun getFavoriteTourism(): Flow<List<TourismEntity>> = tourismDao.getFavoriteTourism()

    suspend fun insertGithub(githubList: List<GithubEntity>) {
        githubDao.insertGithub(githubList)

    }
//    fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean) {
//        tourism.isFavorite = newState
//        tourismDao.updateFavoriteTourism(tourism)
//    }
}