package com.example.hotel_list_feature.domain.repository

import com.example.hotel_list_feature.data.network.model.Hotel

interface HotelRepository {
    suspend fun getHotelList(): List<Hotel>
}