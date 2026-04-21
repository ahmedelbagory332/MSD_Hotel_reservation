package com.example.hotel_list_feature.data.network.api

import com.example.hotel_list_feature.data.network.model.Hotel
import com.example.hotel_list_feature.data.network.model.mockHotels
import kotlinx.coroutines.delay
import javax.inject.Inject

// this class simulate api calling

class HotelApi @Inject constructor() {

    suspend fun getHotelList(): List<Hotel> {
        delay(500) // simulate api calling
        return mockHotels
    }
}