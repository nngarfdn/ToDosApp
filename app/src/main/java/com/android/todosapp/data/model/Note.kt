package com.android.todosapp.data.model

data class Note (
    var userId: Int? = 0,
    var id: Int? = 0,
    var title: String? = "",
    var completed: Boolean? = false
    )