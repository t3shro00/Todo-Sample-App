package com.example.todosampleapp.model

import com.google.gson.annotations.SerializedName

data class Todo(
//    @SerializedName("userId")
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
