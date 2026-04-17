package com.example.search_auto_complete_feature.presentation

import androidx.compose.runtime.Stable
import com.example.core.base.presentation.ViewState

@Stable
data class SearchState(
    val suggestions: List<String> = emptyList(),
    val isLoading: Boolean = false
): ViewState