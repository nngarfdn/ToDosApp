package com.android.todosapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.todosapp.data.model.Note
import com.android.todosapp.databinding.ItemNoteBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.RecentAdapterViewHolder>() {
    inner class RecentAdapterViewHolder(val view: ItemNoteBinding) :
        RecyclerView.ViewHolder(view.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(list: MutableList<Note>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentAdapterViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentAdapterViewHolder, position: Int) {
        holder.view.apply {
            val data = differ.currentList[position]
            txtTitle.text = data.title
            txtContent.text =
                if (data.completed == true) "Status: Completed" else "Status: Not Completed"
            root.setOnClickListener { }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

}