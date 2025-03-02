package com.example.todo.data.todo

import android.content.Context
import androidx.room.Room
import com.example.todo.data.database.Task
import com.example.todo.data.database.ToDoDao
import com.example.todo.data.database.ToDoDatabase

class ToDoRepository(
    val toDoDatabase: ToDoDatabase,
    private val toDoDao: ToDoDao = toDoDatabase.toDoDao()
) {

    suspend fun addTask(name: String, desc: String) {
        toDoDao.insertTask(
            Task(
                name = name,
                desc = desc,
            )
        )
    }

    suspend fun getAllTasks(): List<Task> {
        return toDoDao.getTasks()
    }

    suspend fun deleteTask(task: Task) {
        toDoDao.deleteTask(task = task)
    }


}