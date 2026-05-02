package com.example.hotel_details_feature.di

import com.example.hotel_details_feature.data.network.api.HotelDetailsApi
import com.example.hotel_details_feature.data.network.repository.HotelDetailsRepositoryImpl
import com.example.hotel_details_feature.domain.repository.HotelDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHotelRepository(
        api: HotelDetailsApi,
    ): HotelDetailsRepository = HotelDetailsRepositoryImpl(
        api = api,
    )
}