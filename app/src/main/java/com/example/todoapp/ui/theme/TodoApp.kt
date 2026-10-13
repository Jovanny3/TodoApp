package com.example.todoapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.theme.TaskViewModel
import com.jovanny.todoapp.ui.AddTaskBar
import com.jovanny.todoapp.ui.TaskListScreen

@Composable
fun TodoApp(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = viewModel()
) {
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    var newTitle by rememberSaveable { mutableStateOf("") }

    val done = tasks.count { it.isDone }
    val total = tasks.size

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$done de $total concluídas",
                style = MaterialTheme.typography.titleMedium
            )
            TextButton(onClick = { viewModel.clearCompleted() }) {
                Text("Limpar concluídas")
            }
        }
        AddTaskBar(
            value = newTitle,
            onValueChange = { newTitle = it },
            onAdd = {
                viewModel.addTask(newTitle)
                newTitle = ""
            }
        )
        TaskListScreen(
            tasks = tasks,
            onToggle = viewModel::toggleTask,
            onDelete = viewModel::deleteTask
        )
    }
}