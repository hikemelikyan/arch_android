package com.hmelikyan.architecture.shared.helpers

interface DiffUtilItemModel {
    fun isTheSame(second: Any):Boolean
    fun isContentsTheSame(second: Any):Boolean
}

inline fun <reified T : Any> T.isTheSame(second: Any): Boolean {
    return second is T
}

inline fun <reified T> T.isContentsTheSame(second: Any): Boolean {
    return try {
        this == second as T
    } catch (e: Exception) {
        false
    }
}