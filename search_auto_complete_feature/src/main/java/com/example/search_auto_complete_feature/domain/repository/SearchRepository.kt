package com.example.search_auto_complete_feature.domain.repository

interface SearchRepository {
    suspend fun preFetchSuggestions(query: String): List<String>
    suspend fun getTrendingQueries(): List<String>
    suspend fun fetchSuggestions(query: String): List<String>
}