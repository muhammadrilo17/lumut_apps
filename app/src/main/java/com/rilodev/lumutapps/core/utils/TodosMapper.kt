package com.rilodev.lumutapps.core.utils

import com.rilodev.lumutapps.core.data.remote.response.TodoResponse
import com.rilodev.lumutapps.core.domain.model.TodoModel

object TodosMapper {
    fun mapResponseToModel(input: TodoResponse): TodoModel {
        return TodoModel(
            id = input.id,
            userId = input.userId,
            title = input.title,
            completed = input.completed,
        )
    }
}