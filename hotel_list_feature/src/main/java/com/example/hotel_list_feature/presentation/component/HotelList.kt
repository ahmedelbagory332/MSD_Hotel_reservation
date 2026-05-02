package com.example.hotel_list_feature.presentation.component

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hotel_list_feature.data.network.model.Hotel

@Composable
fun HotelList(
    listState: LazyListState,
    hotels: List<Hotel>,
    context: Context,
    modifier: Modifier = Modifier,
    onHotelClick: () -> Unit
) {
    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = hotels,
            key = { it.id }
        ) { hotel ->
            HotelItem(hotel = hotel, context = context) {
                onHotelClick()
            }
        }
    }
}