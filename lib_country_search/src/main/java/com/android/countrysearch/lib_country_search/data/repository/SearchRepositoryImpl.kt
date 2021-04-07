package com.android.countrysearch.lib_country_search.data.repository

import com.android.countrysearch.lib_country_search.data.contract.cache.CountryListCache
import com.android.countrysearch.lib_country_search.data.contract.remote.SearchRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryEntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class SearchRepositoryImpl @Inject constructor(
    private val searchRemote: SearchRemote,
     private val countryListCache: CountryListCache,
    private val countryEntityMapper: CountryEntityMapper
) : SearchRepository {

    override fun searchCountries(countryName: String): Flow<List<Country>> {
        return flow {
            val cachedCountry = countryListCache.getCountryByName(countryName)
            if (cachedCountry.isNotEmpty()) {
                emit(countryEntityMapper.mapFromEntityList(cachedCountry))
            }else {
                val countries: List<CountryEntity>? = searchRemote.searchCountries(countryName)
                emit(countryEntityMapper.mapFromEntityList(countries ?: emptyList()))
            }
        }
    }
}
