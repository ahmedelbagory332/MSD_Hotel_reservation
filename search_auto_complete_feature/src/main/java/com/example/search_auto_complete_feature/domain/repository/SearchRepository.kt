package com.example.search_auto_complete_feature.domain.repository

interface SearchRepository {
    suspend fun getTrendingQueries(): List<String>
    suspend fun fetchSuggestions(query: String): List<String>
}