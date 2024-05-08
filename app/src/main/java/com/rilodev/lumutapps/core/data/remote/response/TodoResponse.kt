package com.rilodev.lumutapps.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TodoResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("completed")
    val completed: Boolean,
)