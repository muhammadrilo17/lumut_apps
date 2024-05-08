package com.rilodev.lumutapps.core.data

import com.rilodev.lumutapps.core.data.remote.network.ApiResponse
import com.rilodev.lumutapps.core.data.remote.RemoteDataSource
import com.rilodev.lumutapps.core.domain.model.TodoModel
import com.rilodev.lumutapps.core.domain.repository.ILumutAppsRepository
import com.rilodev.lumutapps.core.utils.TodosMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class LumutAppsRepository(
    private val remoteDataSource: RemoteDataSource
): ILumutAppsRepository {
    override fun getTodos(): Flow<Resource<List<TodoModel>>> {
        return flow {
            try {
                when (val response = remoteDataSource.getTodos().first()) {
                    is ApiResponse.Success -> {
                        val result = response.data.map {
                            TodosMapper.mapResponseToModel(it)
                        }
                        emit(Resource.Success(result))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(response.errorMessage))
                    }
                    is ApiResponse.Empty -> {}
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override fun getTodosById(id: Int): Flow<Resource<TodoModel>> {
        return flow {
            try {
                when (val response = remoteDataSource.getTodosById(id).first()) {
                    is ApiResponse.Success -> {
                        val result = TodosMapper.mapResponseToModel(response.data)
                        emit(Resource.Success(result))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(response.errorMessage))
                    }
                    is ApiResponse.Empty -> {}
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}