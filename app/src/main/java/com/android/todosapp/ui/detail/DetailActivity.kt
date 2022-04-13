package com.android.todosapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.todosapp.R
import com.android.todosapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}