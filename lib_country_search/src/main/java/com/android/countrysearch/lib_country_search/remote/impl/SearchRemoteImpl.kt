package com.android.countrysearch.lib_country_search.remote.impl

import com.android.countrysearch.lib_country_search.data.contract.remote.SearchRemote
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.remote.ApiService
import com.android.countrysearch.lib_country_search.remote.mapper.CountryRemoteModelMapper
import javax.inject.Inject

internal class SearchRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val countryRemoteModelMapper: CountryRemoteModelMapper
) : SearchRemote {

    override suspend fun searchCountries(countryName: String): List<CountryEntity>? {
        return apiService.searchCountries(countryName, getFields())
            ?.map(countryRemoteModelMapper::mapFromModel)
    }

    private fun getFields(): String{
        return "name;flag;currencies;capital"
    }

}
