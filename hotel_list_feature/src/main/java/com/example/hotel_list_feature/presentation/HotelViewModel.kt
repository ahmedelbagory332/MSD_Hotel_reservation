package com.example.hotel_list_feature.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.base.network.NetWorkCall
import com.example.core.base.presentation.BaseViewModel
import com.example.hotel_list_feature.domain.use_case.GetHotelListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelListUseCase: GetHotelListUseCase,
) : BaseViewModel<HotelState, HotelIntent>(initialState = HotelState()) {

    override fun sendIntent(intent: HotelIntent) {
        when (intent) {
            is HotelIntent.GetHotelList -> getHotelList()
        }
    }

    init {
        sendIntent(HotelIntent.GetHotelList)
    }

    private fun getHotelList() {
        viewModelScope.launch {
            getHotelListUseCase(Unit).collect { result ->
                when (result) {
                    is NetWorkCall.Loading -> {
                        updateState { it.copy(isLoading = true) }
                    }

                    is NetWorkCall.Success -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                hotels = result.data?.toList() ?: emptyList(),
                                error = ""
                            )
                        }
                    }

                    is NetWorkCall.Error -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                hotels = emptyList(),
                                error = result.message ?: "An unexpected error happened"
                            )
                        }
                    }
                }
            }
        }
    }
}

