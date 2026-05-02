package com.example.hotel_details_feature.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.base.network.NetWorkCall
import com.example.core.base.presentation.BaseViewModel
import com.example.hotel_details_feature.data.network.model.HotelDetails
import com.example.hotel_details_feature.domain.use_case.GetHotelDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelDetailsViewModel @Inject constructor(
    private val getHotelDetailsUseCase: GetHotelDetailsUseCase,
) : BaseViewModel<HotelDetailsState, HotelDetailsIntent>(initialState = HotelDetailsState()) {

    override fun sendIntent(intent: HotelDetailsIntent) {
        when (intent) {
            is HotelDetailsIntent.GetHotelDetails -> getHotelList()
        }
    }

    init {
        sendIntent(HotelDetailsIntent.GetHotelDetails)
    }

    private fun getHotelList() {
        viewModelScope.launch {
            getHotelDetailsUseCase(Unit).collect { result ->
                when (result) {
                    is NetWorkCall.Loading -> {
                        updateState { it.copy(isLoading = true) }
                    }

                    is NetWorkCall.Success -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                hotel = result.data ?: HotelDetails(),
                                error = ""
                            )
                        }
                    }

                    is NetWorkCall.Error -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                hotel = HotelDetails(),
                                error = result.message ?: "An unexpected error happened"
                            )
                        }
                    }
                }
            }
        }
    }
}

