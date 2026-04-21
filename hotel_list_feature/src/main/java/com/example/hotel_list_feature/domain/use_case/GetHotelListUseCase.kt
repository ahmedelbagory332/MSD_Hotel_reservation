package com.example.hotel_list_feature.domain.use_case

import com.example.core.base.domain.BaseUseCase
import com.example.hotel_list_feature.data.network.model.Hotel
import com.example.hotel_list_feature.domain.repository.HotelRepository
import javax.inject.Inject

class GetHotelListUseCase @Inject constructor(
    private val repository: HotelRepository
) : BaseUseCase<List<Hotel>, Unit>() {

    override suspend fun execute(params: Unit): List<Hotel> {
        return repository.getHotelList()
    }
}