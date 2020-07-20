package com.hmelikyan.architecture.model

data class ResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)