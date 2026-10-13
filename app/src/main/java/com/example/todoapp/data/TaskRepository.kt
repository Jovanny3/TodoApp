package com.example.todoapp.data

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TaskRepository @Inject constructor(
    private val dao: TaskDao
) {
    val tasks: Flow<List<Task>> = dao.observeAll()

    suspend fun add(title: String) {
        dao.insert(Task(title = title))
    }

    suspend fun toggle(task: Task) {
        dao.update(task.copy(isDone = !task.isDone))
    }

    suspend fun delete(task: Task) {
        dao.delete(task)
    }

    // Dentro do seu TaskRepository ou TaskDao
    suspend fun clearCompleted() {
        // Código para apagar da base de dados local, por exemplo:
        dao.deleteCompleted()

    }


}