package com.android.countrysearch.cache.di

import android.content.Context
import com.android.countrysearch.cache.room.CountryDetailDao
import com.android.countrysearch.cache.room.CountriesDatabase
import com.android.countrysearch.cache.room.CountryListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
internal object CacheModule {
    @[Provides Singleton]
    fun provideDatabase(@ApplicationContext context: Context): CountriesDatabase {
        return CountriesDatabase.build(context)
    }

    @[Provides Singleton]
    fun provideCountryListDao(countriesDatabase: CountriesDatabase): CountryListDao {
        return countriesDatabase.countryListDao
    }

    @[Provides Singleton]
    fun provideCountryDetailDao(countriesDatabase: CountriesDatabase): CountryDetailDao {
        return countriesDatabase.countryDetailDao
    }
}
