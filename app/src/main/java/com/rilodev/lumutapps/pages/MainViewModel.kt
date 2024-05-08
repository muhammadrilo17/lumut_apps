package com.rilodev.lumutapps.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rilodev.lumutapps.core.data.Resource
import com.rilodev.lumutapps.core.domain.model.TodoModel
import com.rilodev.lumutapps.core.domain.repository.ILumutAppsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ILumutAppsRepository): ViewModel() {
    private val _todos = MutableStateFlow<Resource<List<TodoModel>>>(Resource.Loading())
    val todos: Flow<Resource<List<TodoModel>>> = _todos

    fun todos() {
        viewModelScope.launch {
            repository.getTodos().collect {
                _todos.value = it
            }
        }
    }

    private val _todosById = MutableStateFlow<Resource<TodoModel>>(Resource.Loading())
    val todosById: Flow<Resource<TodoModel>> = _todosById

    fun todosById(id: Int) {
        viewModelScope.launch {
            repository.getTodosById(id).collect {
                _todosById.value = it
            }
        }
    }
}