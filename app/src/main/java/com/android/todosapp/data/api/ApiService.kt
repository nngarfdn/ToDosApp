package com.android.todosapp.data.api

import com.android.todosapp.data.model.ListNote
import com.android.todosapp.data.model.Note
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("todos")
    suspend fun getListTodos(): List<Note>

    @GET("todos/{id}")
    suspend fun getDetailTodo(@Path("id") id: String): Note


}