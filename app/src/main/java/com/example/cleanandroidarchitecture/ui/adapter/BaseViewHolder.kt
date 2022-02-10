package com.example.cleanandroidarchitecture.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanandroidarchitecture.model.UiState

abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract fun <T : UiState> bind(state: T)
}