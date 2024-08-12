package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    fun getAllGithub(querry: String): Flow<Resource<List<Github>>>

    fun getDetailUser(username: String) : Flow<Resource<GithubDetail>>

//    fun getFavoriteTourism(): Flow<List<Tourism>>

//    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}