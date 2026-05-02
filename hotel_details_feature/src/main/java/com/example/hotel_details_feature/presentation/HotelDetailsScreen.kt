package com.example.hotel_details_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hotel_details_feature.presentation.component.AmenitiesSection
import com.example.hotel_details_feature.presentation.component.BookingBottomBar
import com.example.hotel_details_feature.presentation.component.DescriptionSection
import com.example.hotel_details_feature.presentation.component.HotelHeader
import com.example.hotel_details_feature.presentation.component.HotelInfoSection


@Composable
fun HotelDetailsScreen(viewModel: HotelDetailsViewModel = hiltViewModel()) {

    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current
    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        uiState.error.isNotEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Text(
                    text = uiState.error,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    ),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.Center)
                )
            }
        }

        else -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 100.dp) // Space for bottom bar
                ) {
                    // 1. Hero Image Header
                    item {
                        HotelHeader(uiState.hotel.imageUrl, context)
                    }

                    // 2. Title & Location Section
                    item {
                        HotelInfoSection(uiState.hotel)
                    }

                    // 3. Amenities Section
                    item {
                        AmenitiesSection(uiState.hotel.amenities)
                    }

                    // 4. Description Section
                    item {
                        DescriptionSection(uiState.hotel.description)
                    }
                }

                // 5. Floating Bottom Booking Bar
                BookingBottomBar(
                    uiState.hotel.price,
                    onClick = {},
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

