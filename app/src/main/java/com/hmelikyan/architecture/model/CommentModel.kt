package com.hmelikyan.architecture.model

import com.hmelikyan.architecture.shared.helpers.DiffUtilItemModel

data class CommentModel(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
) : DiffUtilItemModel {

    override fun isTheSame(second: Any): Boolean {
        return try {
            id == (second as CommentModel).id
        } catch (e: Exception) {
            false
        }
    }

    override fun isContentsTheSame(second: Any): Boolean {
        second as CommentModel
        return id == second.id &&
                email == second.email &&
                name == second.name &&
                postId == second.postId &&
                body == second.body
    }
}