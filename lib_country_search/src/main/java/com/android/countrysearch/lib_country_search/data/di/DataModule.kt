package com.android.countrysearch.lib_country_search.data.di

import com.android.countrysearch.lib_country_search.data.repository.CountryDetailRepositoryImpl
import com.android.countrysearch.lib_country_search.data.repository.CountryListRepositoryImpl
import com.android.countrysearch.lib_country_search.data.repository.SearchRepositoryImpl
import com.android.countrysearch.lib_country_search.domain.repository.CountryDetailRepository
import com.android.countrysearch.lib_country_search.domain.repository.CountryListRepository
import com.android.countrysearch.lib_country_search.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface DataModule {

    @get:Binds
    val SearchRepositoryImpl.searchRepository: SearchRepository

    @get:Binds
    val CountryDetailRepositoryImpl.countryDetailRepository: CountryDetailRepository

    @get:Binds
    val CountryListRepositoryImpl.countryListRepository: CountryListRepository
}
