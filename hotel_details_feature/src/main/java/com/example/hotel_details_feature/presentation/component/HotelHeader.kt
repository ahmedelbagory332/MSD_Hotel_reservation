package com.example.hotel_details_feature.presentation.component

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.core.base.presentation.getCoilImageLoader


@Composable
fun HotelHeader(imageUrl: String, context: Context) {
    AsyncImage(
        model = imageUrl,
        contentDescription = imageUrl,
        contentScale = ContentScale.Crop,
        imageLoader = getCoilImageLoader(context),
    )
}
