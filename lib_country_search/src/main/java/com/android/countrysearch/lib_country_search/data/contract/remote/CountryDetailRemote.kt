package com.android.countrysearch.lib_country_search.data.contract.remote

import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity

internal interface CountryDetailRemote {

    suspend fun fetchCountryByName(countryName: String): CountryDetailEntity

}
