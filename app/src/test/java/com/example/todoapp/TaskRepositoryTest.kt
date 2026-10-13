package com.example.todoapp

import com.example.todoapp.data.TaskRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TaskRepositoryTest {

    private lateinit var repository: TaskRepository

    @Before
    fun setup() {
        repository = TaskRepository(FakeTaskDao())
    }

    @Test
    fun add_insertsTaskNotDone() = runTest {
        repository.add("Estudar Kotlin")

        val tasks = repository.tasks.first()

        assertEquals(1, tasks.size)
        assertEquals("Estudar Kotlin", tasks[0].title)
        assertFalse(tasks[0].isDone)
    }

    @Test
    fun toggle_flipsIsDone() = runTest {
        repository.add("A")
        val task = repository.tasks.first().first()

        repository.toggle(task)

        assertTrue(repository.tasks.first().first().isDone)
    }

    @Test
    fun delete_removesOnlyThatTask() = runTest {
        repository.add("A")
        repository.add("B")
        val first = repository.tasks.first().first()

        repository.delete(first)

        assertEquals(listOf("B"), repository.tasks.first().map { it.title })
    }

    @Test
    fun clearCompleted_removesOnlyDoneTasks() = runTest {
        repository.add("A")
        repository.add("B")
        val first = repository.tasks.first().first()
        repository.toggle(first)

        repository.clearCompleted()

        assertEquals(listOf("B"), repository.tasks.first().map { it.title })
    }
}