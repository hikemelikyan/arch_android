package com.hmelikyan.architecture.model

import com.hmelikyan.architecture.shared.helpers.DiffUtilItemModel

data class ResponseItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
) : DiffUtilItemModel {
    override fun isTheSame(second: Any): Boolean {
        return try {
            id == (second as ResponseItem).id
        } catch (e: Exception) {
            false
        }
    }

    override fun isContentsTheSame(second: Any): Boolean {
        second as ResponseItem
        return id == second.id &&
                completed == second.completed &&
                title == second.title &&
                userId == userId
    }
}