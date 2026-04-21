package com.example.hotel_list_feature.presentation

import com.example.core.base.presentation.ViewState
import com.example.hotel_list_feature.data.network.model.Hotel
import javax.annotation.concurrent.Immutable

@Immutable
data class HotelState(
    val isLoading: Boolean = false,
    val hotels: List<Hotel> = emptyList(),
    val error: String = ""
) : ViewState