package com.devfares.interviewtaskadvansyswithvodafone.data.di


import android.content.Context
import com.devfares.interviewtaskadvansyswithvodafone.data.di.NetworkModule
import com.devfares.interviewtaskadvansyswithvodafone.data.respositories.WeatherRepositoryImpl
import com.devfares.interviewtaskadvansyswithvodafone.data.sources.local.DataStoreManager
import com.devfares.interviewtaskadvansyswithvodafone.domain.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository {
        return weatherRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

}
