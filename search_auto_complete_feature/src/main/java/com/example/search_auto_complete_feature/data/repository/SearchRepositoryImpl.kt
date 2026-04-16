package com.example.search_auto_complete_feature.data.repository

import com.example.core.base.network.safeApiCall
import com.example.search_auto_complete_feature.data.local.dao.SearchDao
import com.example.search_auto_complete_feature.data.local.entity.SearchSuggestionEntity
import com.example.search_auto_complete_feature.data.network.api.SearchApi
import com.example.search_auto_complete_feature.domain.repository.SearchRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi,
    private val dao: SearchDao,
    private val gson: Gson
) : SearchRepository {

    override suspend fun getTrendingQueries(): List<String> {
        return safeApiCall { api.getTrendingQueries() }
    }

    override suspend fun fetchSuggestions(query: String): List<String> {
        val q = query.lowercase().trim()
        // first look in cache
        val cached = dao.getSuggestions(q)
        if (cached != null) {
            // update time in cache
            dao.saveSuggestions(cached.copy(lastUsedTimestamp = System.currentTimeMillis()))
            return safeApiCall {
                gson.fromJson(
                    cached.suggestionsJson,
                    object : TypeToken<List<String>>() {}.type
                )
            }
        }
        // get from api if not in cache
        val remoteData = api.fetchSuggestions(q)
        if (remoteData.isNotEmpty()) {
            dao.saveSuggestions(SearchSuggestionEntity(q, gson.toJson(remoteData)))
        }
        return safeApiCall { remoteData }
    }
}