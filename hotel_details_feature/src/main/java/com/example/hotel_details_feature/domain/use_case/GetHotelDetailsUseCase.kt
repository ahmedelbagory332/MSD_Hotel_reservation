package com.example.hotel_details_feature.domain.use_case

import com.example.core.base.domain.BaseUseCase
import com.example.hotel_details_feature.data.network.model.HotelDetails
import com.example.hotel_details_feature.domain.repository.HotelDetailsRepository
import javax.inject.Inject

class GetHotelDetailsUseCase @Inject constructor(
    private val repository: HotelDetailsRepository
) : BaseUseCase<HotelDetails, Unit>() {

    override suspend fun execute(params: Unit): HotelDetails {
        return repository.getHotelDetails()
    }
}