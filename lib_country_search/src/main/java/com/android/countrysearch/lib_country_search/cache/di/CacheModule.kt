package com.android.countrysearch.lib_country_search.cache.di

import com.android.countrysearch.lib_country_search.cache.impl.CountryDetailCacheImpl
import com.android.countrysearch.lib_country_search.cache.impl.CountryListCacheImpl
import com.android.countrysearch.lib_country_search.data.contract.cache.CountryDetailCache
import com.android.countrysearch.lib_country_search.data.contract.cache.CountryListCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface CacheModule {

    @get:Binds
    val CountryListCacheImpl.countryListCache: CountryListCache

    @get:Binds
    val CountryDetailCacheImpl.countryDetailCache: CountryDetailCache
}
