package com.example.search_auto_complete_feature.domain.use_case

import com.example.core.base.domain.BaseUseCase
import com.example.search_auto_complete_feature.domain.repository.SearchRepository
import javax.inject.Inject

class GetTrendingQueriesUseCase @Inject constructor(
    private val repository: SearchRepository
) : BaseUseCase<List<String>, Unit>() {

    override suspend fun execute(params: Unit): List<String> {
        return repository.getTrendingQueries()
    }
}