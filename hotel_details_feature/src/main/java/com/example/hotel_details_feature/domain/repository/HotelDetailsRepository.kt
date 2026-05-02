package com.example.hotel_details_feature.domain.repository

import com.example.hotel_details_feature.data.network.model.HotelDetails

interface HotelDetailsRepository {
    suspend fun getHotelDetails(): HotelDetails
}