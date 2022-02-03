package com.example.cleanandroidarchitecture.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanandroidarchitecture.AppConstants
import com.example.cleanandroidarchitecture.R
import com.example.cleanandroidarchitecture.databinding.ItemPostBinding
import com.example.cleanandroidarchitecture.ui.viewmodel.PostItemUiState

class PostAdapter(
    private var dataSet: Array<PostItemUiState> = emptyArray()
): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = DataBindingUtil
            .inflate<ItemPostBinding>(layoutInflater, R.layout.item_post, parent, false)
        return ViewHolder(binding = itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun submitDataSet(data: Array<PostItemUiState>) {
        dataSet = data
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                Log.d(AppConstants.TAG_APPLICATION, "click")
                binding.state?.onClick?.invoke()
            }
        }

        fun bind(state: PostItemUiState) {
            binding.state = state
            binding.executePendingBindings()
        }
    }

}