package com.example.search_auto_complete_feature.data.network.api

import com.example.search_auto_complete_feature.data.network.model.mockData
import com.example.search_auto_complete_feature.data.network.model.mockSuggestionsData
import kotlinx.coroutines.delay
import javax.inject.Inject

// this class simulate api calling

class SearchApi @Inject constructor() {

    suspend fun preFetchSuggestions(query: String): List<String> {
        delay(500) // simulate api calling
        return mockSuggestionsData[query.lowercase()] ?: emptyList()
    }

    suspend fun getTrendingQueries(): List<String> {
        delay(500) // simulate api calling
        return listOf("c", "ca", "cai")
    }

    suspend fun fetchSuggestions(query: String): List<String> {
        delay(500) // simulate api calling
        return mockData[query.lowercase()] ?: emptyList()
    }
}