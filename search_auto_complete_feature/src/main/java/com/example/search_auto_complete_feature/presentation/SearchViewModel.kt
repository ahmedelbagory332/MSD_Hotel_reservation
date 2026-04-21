package com.example.search_auto_complete_feature.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.base.network.NetWorkCall
import com.example.core.base.presentation.BaseViewModel
import com.example.search_auto_complete_feature.domain.use_case.FetchSuggestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchSuggestionsUseCase: FetchSuggestionsUseCase,
) : BaseViewModel<SearchState, SearchIntent>(initialState = SearchState()) {

    // we made query out the state to avoid recomposition if any of val of state changes and query not
    private val _query = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query

    override fun sendIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.OnQueryChanged -> onQueryChanged(newQuery = intent.newQuery)
            is SearchIntent.ObserveQuery -> observeQuery()
        }
    }

    init {
        sendIntent(SearchIntent.ObserveQuery)
    }

    private fun observeQuery() {
        query
            .debounce(300)
            .filter { it.isNotBlank() }
            .flatMapLatest { q ->
                fetchSuggestionsUseCase(q)
            }
            .onEach { result ->
                when (result) {
                    is NetWorkCall.Loading -> {
                        updateState { it.copy(isLoading = true) }
                    }

                    is NetWorkCall.Success -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                suggestions = result.data?.toList() ?: emptyList()
                            )
                        }
                    }

                    is NetWorkCall.Error -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                suggestions = emptyList()
                            )
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
        if (newQuery.isBlank()) {
            updateState { it.copy(suggestions = emptyList()) }
        }
    }

}

