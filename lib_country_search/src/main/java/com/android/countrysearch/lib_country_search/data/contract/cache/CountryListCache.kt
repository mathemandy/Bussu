package com.android.countrysearch.lib_country_search.data.contract.cache

import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.domain.model.Country

internal interface CountryListCache {
    suspend fun saveCountry(country: CountryEntity)
    suspend fun saveCountries(country: List<CountryEntity>)
    suspend fun getSavedCountries(): List<CountryEntity>
    suspend fun getCountryByName(name: String): List<CountryEntity>
    suspend fun clearCountries()
}
