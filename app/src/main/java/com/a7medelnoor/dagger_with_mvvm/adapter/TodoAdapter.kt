package com.a7medelnoor.dagger_with_mvvm.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.a7medelnoor.dagger_with_mvvm.databinding.TodoLayoutAdapterBinding
import com.a7medelnoor.dagger_with_mvvm.model.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoAdapterViewHolder>() {
    inner class TodoAdapterViewHolder(val binding: TodoLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapterViewHolder {
        return TodoAdapterViewHolder(
            TodoLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoAdapterViewHolder, position: Int) {
        val todo = differ.currentList[position]
        holder.binding.apply {
            tvTodoTitle.text = todo.todoTitle
        }
        holder.binding.checkBox.apply {
            setOnClickListener {
                holder.binding.apply {
                    if (isChecked) {
                        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or
                                Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and
                                Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }
                }
            }
        }
    }

    override fun getItemCount() = differ.currentList.size
}


