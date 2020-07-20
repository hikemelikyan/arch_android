package com.hmelikyan.architecture.shared.helpers

import androidx.recyclerview.widget.DiffUtil

open class DefaultDiffUtilCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is DiffUtilItemModel) {
            oldItem.isTheSame(newItem)
        } else {
            oldItem.isTheSame(newItem)
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is DiffUtilItemModel) {
            oldItem.isContentsTheSame(newItem)
        } else {
            oldItem.isContentsTheSame(newItem)
        }
    }
}