package com.android.countrysearch.lib_country_search.data.contract.remote

import com.android.countrysearch.lib_country_search.data.model.CountryEntity

internal interface SearchRemote {
    suspend fun searchCountries(countryName: String): List<CountryEntity>?
}
