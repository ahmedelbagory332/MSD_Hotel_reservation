package com.example.hotel_details_feature.data.network.model

data class HotelDetails(
    val name: String = "",
    val location: String = "",
    val rating: Double = 0.0,
    val price: String = "",
    val description: String = "",
    val amenities: List<String> = emptyList(),
    val imageUrl: String = ""
)

val mockHotelDetails =  HotelDetails(
    name = "The Azure Sands Resort",
    location = "Maldives, North Malé Atoll",
    rating = 4.9,
    price = "$850",
    description = "Experience ultimate luxury in our overwater bungalows. Wake up to the sound of the Indian Ocean and enjoy private deck access to crystal clear lagoons. Our world-class spa and underwater dining offer an unforgettable escape from the everyday.",
    amenities = listOf("Private Pool", "Spa", "Ocean View", "Free Wi-Fi", "Airport Shuttle"),
    imageUrl = "https://images.unsplash.com/photo-1540541338287-41700207dee6"
)