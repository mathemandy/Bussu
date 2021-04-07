package com.android.countrysearch.lib_country_search.remote.di

import com.android.countrysearch.lib_country_search.BuildConfig
import com.android.countrysearch.lib_country_search.data.contract.remote.CountryDetailRemote
import com.android.countrysearch.lib_country_search.data.contract.remote.CountryRemote
import com.android.countrysearch.lib_country_search.data.contract.remote.SearchRemote
import com.android.countrysearch.lib_country_search.remote.ApiService
import com.android.countrysearch.lib_country_search.remote.impl.CountryDetailRemoteImpl
import com.android.countrysearch.lib_country_search.remote.impl.CountryRemoteImpl
import com.android.countrysearch.lib_country_search.remote.impl.SearchRemoteImpl
import com.android.countrysearch.remote.RemoteFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface RemoteModule {

    @get:Binds
    val SearchRemoteImpl.bindSearchRemote: SearchRemote

    @get:Binds
    val CountryDetailRemoteImpl.bindCountryDetailRemote: CountryDetailRemote

    @get:Binds
    val CountryRemoteImpl.bindCountryRemote: CountryRemote


    companion object {
        @[Provides Singleton]
        fun apiService(remoteFactory: RemoteFactory): ApiService =
            remoteFactory.createRetrofit(
                url = BuildConfig.BASE_URL,
                isDebug = BuildConfig.DEBUG
            ).create(ApiService::class.java)
    }
}
