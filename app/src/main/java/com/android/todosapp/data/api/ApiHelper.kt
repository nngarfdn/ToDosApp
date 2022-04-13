package com.android.todosapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper(private val apiService: ApiService) {

    suspend fun getListTodos() = apiService.getListTodos()

    suspend fun getDetailTodo(id: Int) = apiService.getDetailTodo(id.toString())

}