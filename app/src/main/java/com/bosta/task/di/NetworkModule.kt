package com.bosta.task.di

import com.bosta.task.data.repositories.CitiesRepositoryImpl
import com.bosta.task.data.services.CitiesService
import com.bosta.task.domain.repositories.CitiesRepository
import com.bosta.task.domain.usecases.GetAllCitiesUseCase
import com.bosta.task.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideCitiesService(retrofit: Retrofit): CitiesService =
        retrofit.create(CitiesService::class.java)

    @Provides
    @Singleton
    fun provideRepository(citiesService: CitiesService): CitiesRepository =
        CitiesRepositoryImpl(citiesService)

    @Provides
    @Singleton
    fun provideUseCase(citiesRepository: CitiesRepository): GetAllCitiesUseCase =
        GetAllCitiesUseCase(citiesRepository)

}
