package com.example.hotel_list_feature.di

import com.example.hotel_list_feature.data.network.api.HotelApi
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
    fun provideApi(): HotelApi = HotelApi()
}