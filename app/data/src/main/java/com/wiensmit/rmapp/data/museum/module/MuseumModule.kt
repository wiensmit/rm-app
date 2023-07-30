package com.wiensmit.rmapp.data.museum.module

import com.wiensmit.rmapp.data.museum.MuseumDataRepository
import com.wiensmit.rmapp.domain.museum.MuseumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MuseumModule {

    @Binds
    abstract fun bindInstantAppRepository(
        repository: MuseumDataRepository,
    ): MuseumRepository
}
