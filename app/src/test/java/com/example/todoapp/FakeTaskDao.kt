package com.example.todoapp

import com.example.todoapp.data.Task
import com.example.todoapp.data.TaskDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeTaskDao : TaskDao {

    private val data = MutableStateFlow<List<Task>>(emptyList())
    private var nextId = 1

    override fun observeAll(): Flow<List<Task>> = data

    override suspend fun insert(task: Task) {
        data.value = data.value + task.copy(id = nextId++)
    }

    override suspend fun update(task: Task) {
        data.value = data.value.map { if (it.id == task.id) task else it }
    }

    override suspend fun delete(task: Task) {
        data.value = data.value.filter { it.id != task.id }
    }

    override suspend fun deleteCompleted() {
        data.value = data.value.filter { !it.isDone }
    }
}