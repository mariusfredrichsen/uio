package com.example.todo.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ToDoDao {

    // TODO add insert ToDo task
    @Insert
    suspend fun insertTask(task: Task)

    // TODO fetch a list of all ToDos
    @Query("SELECT * FROM task ORDER BY id")
    suspend fun getTasks(): List<Task>

    // TODO delete task
    @Delete
    suspend fun deleteTask(task: Task)
}