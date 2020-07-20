package com.hmelikyan.architecture.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hmelikyan.architecture.databinding.RecyclerCardItemBinding
import com.hmelikyan.architecture.model.ResponseItem
import com.hmelikyan.architecture.shared.helpers.DefaultDiffUtilCallback

class RecyclerAdapter :
    ListAdapter<Any, RecyclerAdapter.ToDoItemViewHolder>(DefaultDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        return ToDoItemViewHolder(
            RecyclerCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = currentList[position] as ResponseItem
        holder.binding.completed.text = item.completed.toString()
        holder.binding.postId.text = item.id.toString()
        holder.binding.userId.text = item.userId.toString()
        holder.binding.title.text = item.title
    }

    class ToDoItemViewHolder(val binding: RecyclerCardItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}