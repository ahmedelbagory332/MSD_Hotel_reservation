package com.example.search_auto_complete_feature.presentation

import com.example.core.base.presentation.ViewState
import javax.annotation.concurrent.Immutable

@Immutable
data class SearchState(
    val suggestions: List<String> = emptyList(),
    val isLoading: Boolean = false
): ViewState