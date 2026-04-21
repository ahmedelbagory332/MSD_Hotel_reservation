package com.example.hotel_list_feature.presentation

import com.example.core.base.presentation.ViewIntent

sealed class HotelIntent() : ViewIntent {
    object GetHotelList : HotelIntent()
}