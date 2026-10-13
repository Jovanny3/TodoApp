package com.example.todoapp.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.todoapp.data.AppDatabase
import com.example.todoapp.data.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        Log.d("DI", "A criar a base de dados")
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo.db"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao()
    }
}