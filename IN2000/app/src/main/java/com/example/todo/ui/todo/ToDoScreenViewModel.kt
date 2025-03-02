package com.example.todo.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.database.Task
import com.example.todo.data.todo.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ToDoScreenViewModel(
    val toDoRepository: ToDoRepository
): ViewModel() {

    private val _toDoScreenUIState: MutableStateFlow<ToDoScreenUIState> = MutableStateFlow(
        ToDoScreenUIState()
    )
    val toDoScreenUIState: StateFlow<ToDoScreenUIState> = _toDoScreenUIState



    init {
        getTasks()
    }


    private fun getTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = toDoRepository.getAllTasks()
            _toDoScreenUIState.update {
                it.copy(
                    tasks = tasks
                )
            }
        }
    }

    fun addTask(name: String, desc: String) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoRepository.addTask(
                name = name,
                desc = desc,
            )
            _toDoScreenUIState.update {
                it.copy(
                    taskName = "",
                    taskDesc = "",
                )
            }
            getTasks()
        }
    }

    fun updateTextName(name: String) {
        viewModelScope.launch {
            _toDoScreenUIState.update {
                it.copy(
                    taskName = name
                )
            }
        }
    }

    fun updateTaskDesc(desc: String) {
        viewModelScope.launch {
            _toDoScreenUIState.update {
                it.copy(
                    taskDesc = desc
                )
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            toDoRepository.deleteTask(
                task = task
            )
            getTasks()
        }
    }
}