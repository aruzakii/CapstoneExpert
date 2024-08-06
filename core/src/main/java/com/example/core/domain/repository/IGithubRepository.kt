package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Github
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    fun getAllGithub(): Flow<Resource<List<Github>>>

//    fun getFavoriteTourism(): Flow<List<Tourism>>

//    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}