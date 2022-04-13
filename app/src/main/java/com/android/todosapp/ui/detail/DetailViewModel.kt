package com.android.todosapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.todosapp.data.repository.ToDoRepository
import com.android.todosapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class DetailViewModel(private val repository: ToDoRepository) : ViewModel() {

    fun getTodoDetail(id: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getDetailTodo(id)))
        }catch (e : Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}