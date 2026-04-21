package com.example.hotel_list_feature.data.network.model

data class Hotel(
    val id: Int,
    val name: String,
    val location: String,
    val rating: Double,
    val image: String
)

val mockHotels = listOf(
    Hotel(
        1,
        "Sunset Paradise Hotel",
        "Hurghada, Egypt",
        4.5,
        "https://picsum.photos/200/200?random=1"
    ),
    Hotel(
        2,
        "Nile View Resort",
        "Cairo, Egypt",
        4.2,
        "https://picsum.photos/200/200?random=2"
    ),
    Hotel(
        3,
        "Desert Rose Lodge",
        "Sharm El Sheikh, Egypt",
        4.7,
        "https://picsum.photos/200/200?random=3"
    ),
    Hotel(
        4,
        "Alex Sea Breeze Hotel",
        "Alexandria, Egypt",
        4.1,
        "https://picsum.photos/200/200?random=4"
    ),
    Hotel(
        5,
        "Oasis Palm Resort",
        "Luxor, Egypt",
        4.6,
        "https://picsum.photos/200/200?random=5"
    )
)
