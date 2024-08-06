package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.GithubResponse
import com.example.core.data.source.remote.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun getListUsers(@Query("q") q: String): GithubResponse
}