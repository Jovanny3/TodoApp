package com.jovanny.todoapp.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todoapp.data.Task


@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onToggle: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(tasks, key = { it.id }) { task ->
            TaskItem(task = task, onToggle = onToggle, onDelete = onDelete)
        }
    }
}