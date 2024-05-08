package com.rilodev.lumutapps.core.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rilodev.lumutapps.core.domain.model.TodoModel
import com.rilodev.lumutapps.databinding.TodoItemBinding
import java.lang.StringBuilder

class TodoAdapter : ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {
    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = getItem(position)
        holder.bind(currentTodo)
    }

    inner class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todoModel: TodoModel) {
            with(binding) {
                textViewTitle.text = StringBuilder(todoModel.title)
                textViewCompleted.text = if (todoModel.completed) "Berhasil" else "Gagal"
            }
        }

        init {
            binding.root.setOnClickListener { _ ->
                onItemClick?.invoke(currentList[adapterPosition].id)
            }
        }
    }

    class TodoDiffCallback : DiffUtil.ItemCallback<TodoModel>() {
        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem == newItem
        }
    }
}
