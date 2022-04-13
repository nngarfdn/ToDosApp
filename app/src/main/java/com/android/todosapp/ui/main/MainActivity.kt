package com.android.todosapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.todosapp.data.api.ApiHelper
import com.android.todosapp.data.api.RetrofitBuilder
import com.android.todosapp.data.model.Note
import com.android.todosapp.databinding.ActivityMainBinding
import com.android.todosapp.ui.base.ViewModelFactory
import com.android.todosapp.utils.Status

class MainActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(MainViewModel::class.java) }
    private lateinit var adapterNote : ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapterNote = ListAdapter()

        binding.apply {
            rvList.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = adapterNote
            }
        }

        viewModel.getListTodos().observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        adapterNote.submitData(resource.data as MutableList<Note>)
                    }

                    Status.ERROR -> {
                        Log.d("ceknote", "onCreate: error ${it.message}")
                    }

                    Status.LOADING -> {
                        Log.d("ceknote", "onCreate: loading note")
                    }
                }
            }
        }
    }


}