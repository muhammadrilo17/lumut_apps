package com.rilodev.lumutapps.core.data.remote.network

import com.rilodev.lumutapps.core.data.remote.response.TodoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<TodoResponse>

    @GET("todos/{id}")
    suspend fun getTodosById(
        @Path("id") id: Int,
    ): TodoResponse
}