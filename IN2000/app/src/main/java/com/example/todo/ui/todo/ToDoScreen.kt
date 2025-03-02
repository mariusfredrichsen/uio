package com.example.todo.ui.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ToDoScreen(
    toDoScreenViewModel: ToDoScreenViewModel
) {
    val toDoUIState by toDoScreenViewModel.toDoScreenUIState.collectAsState()



    Scaffold(
        modifier = Modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            toDoUIState.tasks.forEach { task ->
                Card {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = task.name
                            )
                            Text(
                                text = task.desc
                            )
                        }
                        Button(
                            onClick = {
                                toDoScreenViewModel.deleteTask(
                                    task = task
                                )
                            }
                        ) {
                            Text(
                                text = "Delete"
                            )
                        }
                    }
                }
            }
            TextField(
                value = toDoUIState.taskName,
                onValueChange = { toDoScreenViewModel.updateTextName(it) }
            )
            TextField(
                value = toDoUIState.taskDesc,
                onValueChange = { toDoScreenViewModel.updateTaskDesc(it) }
            )
            Button(
                onClick = {
                    toDoScreenViewModel.addTask(
                        toDoUIState.taskName,
                        toDoUIState.taskDesc,
                    )
                }
            ) {
                Text(
                    text = "Add Task"
                )
            }
        }
    }
}