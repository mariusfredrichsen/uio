package com.example.todo

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.room.Room
import com.example.todo.data.database.ToDoDatabase
import com.example.todo.data.todo.ToDoRepository
import com.example.todo.ui.todo.ToDoScreen
import com.example.todo.ui.todo.ToDoScreenViewModel


@Composable
fun ToDoApp(context: Context) {
    // opprett alt du trenger her
    val toDoDatabase = Room.databaseBuilder(
        context = context,
        ToDoDatabase::class.java, "toDoDatabase"
    ).build()

    val toDoRepository = ToDoRepository(
        toDoDatabase = toDoDatabase
    )

    val toDoScreenViewModel = ToDoScreenViewModel(
        toDoRepository = toDoRepository
    )

    ToDoScreen(
        toDoScreenViewModel
    )
}