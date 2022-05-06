package com.a7medelnoor.dagger_with_mvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.a7medelnoor.dagger_with_mvvm.model.Todo
import java.util.concurrent.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)
    @Query("SELECT * FROM todo ORDER BY todoTitle ASC")
    fun getAllTodos() : kotlinx.coroutines.flow.Flow<List<Todo>>
}