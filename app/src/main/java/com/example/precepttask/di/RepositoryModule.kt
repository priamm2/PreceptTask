package com.example.precepttask.di

import com.example.precepttask.network.ApiService
import com.example.precepttask.network.ApiServiceImpl
import com.example.precepttask.repository.PreceptFactorRepository
import com.example.precepttask.repository.PreceptFactorRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPreceptFactorRepository(
        preceptFactorRepositoryImpl: PreceptFactorRepositoryImpl
    ): PreceptFactorRepository


    @Binds
    @Singleton
    abstract fun provideApiService(apiServiceImpl: ApiServiceImpl): ApiService

}