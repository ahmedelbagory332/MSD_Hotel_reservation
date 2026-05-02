package com.example.hotel_details_feature.presentation

import com.example.core.base.presentation.ViewIntent

sealed class HotelDetailsIntent() : ViewIntent {
    object GetHotelDetails : HotelDetailsIntent()
}