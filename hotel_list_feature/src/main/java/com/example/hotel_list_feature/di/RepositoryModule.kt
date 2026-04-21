package com.example.hotel_list_feature.di

import com.example.hotel_list_feature.data.network.api.HotelApi
import com.example.hotel_list_feature.data.repository.HotelRepositoryImpl
import com.example.hotel_list_feature.domain.repository.HotelRepository
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
        api: HotelApi,
    ): HotelRepository = HotelRepositoryImpl(
        api = api,
    )
}