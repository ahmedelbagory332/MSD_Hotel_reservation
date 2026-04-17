package com.example.search_auto_complete_feature.presentation

import com.example.core.base.presentation.ViewIntent

sealed class SearchIntent() : ViewIntent {
    class OnQueryChanged(val newQuery: String) : SearchIntent()
    object ObserveQuery : SearchIntent()
}