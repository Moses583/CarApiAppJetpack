package com.ravemaster.carapiapp.di

import com.ravemaster.carapiapp.data.repository.CarApiRepositoryImpl
import com.ravemaster.carapiapp.domain.repository.CarApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCarApiRepository(
        carApiRepositoryImpl: CarApiRepositoryImpl
    ): CarApiRepository
}