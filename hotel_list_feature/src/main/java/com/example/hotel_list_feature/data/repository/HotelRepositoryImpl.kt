package com.example.hotel_list_feature.data.repository

import com.example.core.base.network.safeApiCall
import com.example.hotel_list_feature.data.network.api.HotelApi
import com.example.hotel_list_feature.data.network.model.Hotel
import com.example.hotel_list_feature.domain.repository.HotelRepository
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val api: HotelApi,
) : HotelRepository {

    override suspend fun getHotelList(): List<Hotel> {
        return safeApiCall { api.getHotelList() }
    }
}