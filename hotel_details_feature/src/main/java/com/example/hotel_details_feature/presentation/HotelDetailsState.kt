package com.example.hotel_details_feature.presentation

import com.example.core.base.presentation.ViewState
import com.example.hotel_details_feature.data.network.model.HotelDetails
import javax.annotation.concurrent.Immutable

@Immutable
data class HotelDetailsState(
    val isLoading: Boolean = false,
    val hotel: HotelDetails = HotelDetails(),
    val error: String = ""
) : ViewState