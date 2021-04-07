package com.android.countrysearch.lib_country_search.remote.impl

import com.android.countrysearch.lib_country_search.data.contract.remote.CountryRemote
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.remote.ApiService
import com.android.countrysearch.lib_country_search.remote.mapper.CountryRemoteModelMapper
import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import javax.inject.Inject

internal class CountryRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val countryRemoteModelMapper: CountryRemoteModelMapper,
) : CountryRemote {

    override suspend fun fetchCountries(): List<CountryEntity> {
        val countries: List<CountryRemoteModel>? = apiService.fetchAllCountries(getFields())
        return countryRemoteModelMapper.mapModelList(countries ?: emptyList())

    }

    fun getFields(): String{
        return "name;flag"
    }

}