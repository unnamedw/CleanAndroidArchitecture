package com.example.cleanandroidarchitecture.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanandroidarchitecture.AppConstants
import com.example.cleanandroidarchitecture.R
import com.example.cleanandroidarchitecture.databinding.ItemPost2Binding
import com.example.cleanandroidarchitecture.databinding.ItemPostBinding
import com.example.cleanandroidarchitecture.model.PostItemUiState
import com.example.cleanandroidarchitecture.model.UiState

class PostAdapter(
    private var dataSet: Array<PostItemUiState> = emptyArray()
): RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        const val VIEW_TYPE_FIRST = 0
        const val VIEW_TYPE_SECOND = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            VIEW_TYPE_FIRST -> {
                FirstViewHolder(binding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.item_post, parent, false))
            }
            VIEW_TYPE_SECOND ->
                SecondViewHolder(binding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.item_post2, parent, false))
            else -> FirstViewHolder(binding = DataBindingUtil
                .inflate(layoutInflater, R.layout.item_post, parent, false))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return position%2
    }

    fun submitDataSet(data: Array<PostItemUiState>) {
        dataSet = data
        notifyDataSetChanged()
    }

    class FirstViewHolder(
        private val binding: ItemPostBinding) : BaseViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                Log.d(AppConstants.TAG_APPLICATION, "click")
                binding.state?.onClick?.invoke()
            }
        }

        override fun <T : UiState> bind(state: T) {
            binding.state = state as PostItemUiState
            binding.executePendingBindings()
        }
    }

    class SecondViewHolder(
        private val binding: ItemPost2Binding) : BaseViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                Log.d(AppConstants.TAG_APPLICATION, "click")
                binding.state?.onClick?.invoke()
            }
        }

        override fun <T : UiState> bind(state: T) {
            binding.state = state as PostItemUiState
            binding.executePendingBindings()
        }

    }



}