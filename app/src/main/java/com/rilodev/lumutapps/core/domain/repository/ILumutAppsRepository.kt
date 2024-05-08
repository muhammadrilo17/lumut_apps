package com.rilodev.lumutapps.core.domain.repository

import com.rilodev.lumutapps.core.data.Resource
import com.rilodev.lumutapps.core.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface ILumutAppsRepository {
    fun getTodos(): Flow<Resource<List<TodoModel>>>
    fun getTodosById(id: Int): Flow<Resource<TodoModel>>
}