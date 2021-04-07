package com.android.countrysearch.lib_country_search.data.contract.cache

import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity

internal interface CountryDetailCache {
    suspend fun saveCountry(countryDetailEntity: CountryDetailEntity)
    suspend fun fetchCountry(name: String): CountryDetailEntity?
}
