package com.codelabs.foodapp.core.presentation

data class PagingState <T>(
    val items:List<T> = emptyList(),
    var isLoading:Boolean = false,
    val endReached:Boolean = false
)