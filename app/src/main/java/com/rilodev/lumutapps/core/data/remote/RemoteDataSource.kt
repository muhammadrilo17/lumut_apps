package com.rilodev.lumutapps.core.data.remote

import com.rilodev.lumutapps.core.data.remote.network.ApiResponse
import com.rilodev.lumutapps.core.data.remote.network.ApiService
import com.rilodev.lumutapps.core.data.remote.response.TodoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getTodos(): Flow<ApiResponse<List<TodoResponse>>> {
        return flow {
            try {
                val response = apiService.getTodos()
                if (response.isEmpty()) {
                    emit(ApiResponse.Error("Data Kosong"))
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage ?: "Terdapat masalah!"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTodosById(id: Int): Flow<ApiResponse<TodoResponse>> {
        return flow {
            try {
                val response = apiService.getTodosById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.localizedMessage ?: "Terdapat masalah!"))
            }
        }.flowOn(Dispatchers.IO)
    }
}