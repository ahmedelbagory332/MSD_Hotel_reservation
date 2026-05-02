package com.example.hotel_list_feature.presentation.component

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.base.presentation.getCoilImageLoader
import com.example.hotel_list_feature.data.network.model.Hotel

@Composable
fun HotelItem(
    hotel: Hotel,
    context: Context,
    modifier: Modifier = Modifier,
    onHotelClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onHotelClick()
            }
    ) {
        AsyncImage(
            model = hotel.image,
            contentDescription = hotel.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            imageLoader = getCoilImageLoader(context)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = hotel.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )

            Text(
                text = hotel.location,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 1
            )

            Text(
                text = "⭐ ${hotel.rating}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}