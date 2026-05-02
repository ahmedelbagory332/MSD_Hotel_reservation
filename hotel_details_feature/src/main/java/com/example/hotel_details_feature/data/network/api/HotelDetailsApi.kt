package com.example.hotel_details_feature.data.network.api

import com.example.hotel_details_feature.data.network.model.HotelDetails
import com.example.hotel_details_feature.data.network.model.mockHotelDetails
import kotlinx.coroutines.delay
import javax.inject.Inject

// this class simulate api calling

class HotelDetailsApi @Inject constructor() {

    suspend fun getHotelDetails(): HotelDetails {
        delay(500) // simulate api calling
        return mockHotelDetails
    }
}