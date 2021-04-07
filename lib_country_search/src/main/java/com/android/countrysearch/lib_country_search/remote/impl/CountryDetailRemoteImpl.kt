package com.android.countrysearch.lib_country_search.remote.impl

import com.android.countrysearch.lib_country_search.data.contract.remote.CountryDetailRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryDetailEntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.remote.ApiService
import com.android.countrysearch.lib_country_search.remote.mapper.CountryDetailRemoteMapper
import com.android.countrysearch.lib_country_search.remote.mapper.CountryRemoteModelMapper
import com.android.countrysearch.lib_country_search.remote.mapper.CurrencyRemoteMapper
import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import javax.inject.Inject

internal class CountryDetailRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val countryDetailRemoteMapper: CountryDetailRemoteMapper,
    private val countryDetailEntityMapper: CountryDetailEntityMapper
) : CountryDetailRemote {

    override suspend fun fetchCountryByName(countryName: String): CountryDetailEntity {
        val countryDetail: CountryRemoteModel? = apiService.searchCountries(countryName, getFields())?.first()
        return countryDetailEntityMapper.mapToEntity(countryDetailRemoteMapper.mapFromModel(countryDetail))
    }

    fun getFields(): String{
        return "name;flag;currencies;capital"
    }


}
