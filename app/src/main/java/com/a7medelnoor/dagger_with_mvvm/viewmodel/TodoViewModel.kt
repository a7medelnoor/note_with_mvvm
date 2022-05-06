package com.a7medelnoor.dagger_with_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.a7medelnoor.dagger_with_mvvm.model.Todo
import com.a7medelnoor.dagger_with_mvvm.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel
@Inject
constructor(private val todoRepository : TodoRepository):ViewModel()
{

    fun insertTodo(todo: Todo) = viewModelScope.launch {
        todoRepository.insertTodo(todo)
    }
    val getAllTodos = todoRepository.getAllTodos().asLiveData()

    // with dependency injection we don't need viewModelFactory
    // hilt will generate it for us

    // so, we are done with mvvm architecture
    // now let's configure our fragments

}