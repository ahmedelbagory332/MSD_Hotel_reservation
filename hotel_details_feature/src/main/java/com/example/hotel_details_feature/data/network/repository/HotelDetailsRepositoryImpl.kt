package com.example.hotel_details_feature.data.network.repository

import com.example.core.base.network.safeApiCall
import com.example.hotel_details_feature.data.network.api.HotelDetailsApi
import com.example.hotel_details_feature.data.network.model.HotelDetails
import com.example.hotel_details_feature.domain.repository.HotelDetailsRepository
import javax.inject.Inject

class HotelDetailsRepositoryImpl @Inject constructor(
    private val api: HotelDetailsApi
) : HotelDetailsRepository {

    override suspend fun getHotelDetails(): HotelDetails {
        return safeApiCall { api.getHotelDetails() }
    }
}