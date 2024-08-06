package com.example.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.ItemsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllGithub(): Flow<ApiResponse<List<ItemsItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListUsers("kevin")
                val dataArray = response.items
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response.items))
                    } else {
                        emit(ApiResponse.Empty)
                    }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}