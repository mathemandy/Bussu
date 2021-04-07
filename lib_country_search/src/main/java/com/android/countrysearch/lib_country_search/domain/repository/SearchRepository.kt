package com.android.countrysearch.lib_country_search.domain.repository

import com.android.countrysearch.lib_country_search.domain.model.Country
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchCountries(countryName: String): Flow<List<Country>>
}
