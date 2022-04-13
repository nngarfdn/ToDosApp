package com.android.todosapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.todosapp.data.api.ApiHelper
import com.android.todosapp.data.repository.ToDoRepository
import com.android.todosapp.ui.main.MainViewModel


class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(ToDoRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}