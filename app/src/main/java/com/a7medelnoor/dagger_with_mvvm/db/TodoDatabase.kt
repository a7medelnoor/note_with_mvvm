package com.a7medelnoor.dagger_with_mvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.a7medelnoor.dagger_with_mvvm.model.Todo

@Database(entities = [Todo::class], version =1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun todoDao(): TodoDao
}