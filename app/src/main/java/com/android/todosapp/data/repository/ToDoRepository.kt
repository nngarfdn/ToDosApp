package com.android.todosapp.data.repository

import com.android.todosapp.data.api.ApiHelper
import com.android.todosapp.data.api.RetrofitBuilder.apiService

class ToDoRepository(private val apiHelper : ApiHelper) {

    suspend fun getListTodos() = apiHelper.getListTodos()

    suspend fun getDetailTodo(id: Int) = apiHelper.getDetailTodo(id)
}