package com.android.todosapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.todosapp.data.api.ApiHelper
import com.android.todosapp.data.api.RetrofitBuilder
import com.android.todosapp.databinding.ActivityDetailBinding
import com.android.todosapp.ui.base.ViewModelFactory
import com.android.todosapp.utils.Status

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.extras?.getInt("id")
        id?.let {
            viewModel.getTodoDetail(it).observe(this) { data ->
                data?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            binding.llContent.visibility = View.VISIBLE
                            binding.apply {
                                txtTitle.text = data.data?.title
                                txtContent.text =
                                    if (data.data?.completed == true) "Status: Completed" else "Status: Not Completed"
                            }
                        }

                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            binding.llContent.visibility = View.GONE
                            Log.d("ceknote", "onCreate: error ${data.message}")
                        }

                        Status.LOADING -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.llContent.visibility = View.GONE
                            Log.d("ceknote", "onCreate: loading note")
                        }
                    }
                }

            }
        }
    }
}