package com.android.countrysearch.lib_country_search.domain.repository

import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import kotlinx.coroutines.flow.Flow

interface CountryDetailRepository {

    fun getCountryDetail(countryUrl: String): Flow<CountryDetail>

}
