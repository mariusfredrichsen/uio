package com.example.todo.ui.todo

import com.example.todo.data.database.Task


data class ToDoScreenUIState(
    val tasks: List<Task> = listOf(),
    val taskName: String = "",
    val taskDesc: String = "",
)
