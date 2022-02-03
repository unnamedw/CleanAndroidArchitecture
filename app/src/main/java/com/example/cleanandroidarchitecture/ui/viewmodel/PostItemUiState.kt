package com.example.cleanandroidarchitecture.ui.viewmodel

import com.example.cleanandroidarchitecture.model.Post

data class PostItemUiState(
    val post: Post,
    val onClick: () -> Unit
)