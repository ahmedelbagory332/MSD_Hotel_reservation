package com.example.search_auto_complete_feature.domain.use_case

import com.example.core.base.domain.BaseUseCase
import com.example.search_auto_complete_feature.domain.repository.SearchRepository
import javax.inject.Inject

class FetchSuggestionsUseCase @Inject constructor(
    private val repository: SearchRepository
) : BaseUseCase<List<String>, String>() {

    override suspend fun execute(params: String): List<String> {
        return repository.fetchSuggestions(query = params)
    }
}