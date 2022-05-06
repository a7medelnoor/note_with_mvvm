package com.a7medelnoor.dagger_with_mvvm.repository

import androidx.room.Dao
import com.a7medelnoor.dagger_with_mvvm.db.TodoDao
import com.a7medelnoor.dagger_with_mvvm.model.Todo
import javax.inject.Inject

class TodoRepository
@Inject
constructor(private val dao: TodoDao)
{
    suspend fun insertTodo(todo: Todo) = dao.insertTodo(todo)
    fun getAllTodos() = dao.getAllTodos()

}