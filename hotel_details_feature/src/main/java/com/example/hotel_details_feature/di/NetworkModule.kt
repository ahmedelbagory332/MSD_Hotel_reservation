package com.example.hotel_details_feature.di

import com.example.hotel_details_feature.data.network.api.HotelDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): HotelDetailsApi = HotelDetailsApi()
}