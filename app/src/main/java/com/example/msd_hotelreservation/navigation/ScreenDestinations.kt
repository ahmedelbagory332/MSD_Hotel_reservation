package com.example.msd_hotelreservation.navigation

import kotlinx.serialization.Serializable

sealed class ScreenDestinations {

    @Serializable
    data object HotelListScreen : ScreenDestinations()
    @Serializable
    data object HotelDetailsScreen : ScreenDestinations()

}