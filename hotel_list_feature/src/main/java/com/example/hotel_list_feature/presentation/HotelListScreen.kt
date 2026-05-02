package com.example.hotel_list_feature.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.hotel_list_feature.presentation.component.HotelList
import com.example.search_auto_complete_feature.presentation.SearchAutoComplete

@Composable
fun HotelListScreen(
    viewModel: HotelViewModel = hiltViewModel(),
    onHotelClick: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        when {
            uiState.isLoading && uiState.hotels.isEmpty() -> {
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
                Text(
                    text = uiState.error,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            else -> {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    SearchAutoComplete()
                    Spacer(modifier = Modifier.height(20.dp))
                    HotelList(
                        hotels = uiState.hotels,
                        listState = listState,
                        context = context
                    ) {
                        onHotelClick()
                    }
                }
            }
        }

    }
}