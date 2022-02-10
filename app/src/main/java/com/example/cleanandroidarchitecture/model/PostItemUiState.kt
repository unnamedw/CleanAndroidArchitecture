package com.example.cleanandroidarchitecture.model

import com.example.cleanandroidarchitecture.model.Post

abstract class UiState

data class PostItemUiState (
    val post: Post,
    val onClick: () -> Unit
): UiState()
