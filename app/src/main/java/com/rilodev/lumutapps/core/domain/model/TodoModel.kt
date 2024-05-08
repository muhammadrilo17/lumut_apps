package com.rilodev.lumutapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean,
): Parcelable