package com.android.countrysearch.lib_country_search.data.repository

import com.android.countrysearch.lib_country_search.data.contract.cache.CountryListCache
import com.android.countrysearch.lib_country_search.data.contract.remote.CountryRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryEntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CountryListRepositoryImpl @Inject constructor(
    private val countryListCache: CountryListCache,
    private val countryListRemote: CountryRemote,
    private val countryEntityMapper: CountryEntityMapper
) : CountryListRepository {

    override fun getCountryList(): Flow<List<Country>> {
        return flow {
            val cachedCountry = countryListCache.getSavedCountries()
            if (cachedCountry.isNotEmpty()) {
                emit(countryEntityMapper.mapFromEntityList(cachedCountry))
            }else  {
                val countryList: List<CountryEntity> = countryListRemote.fetchCountries()
                emit(countryList.map(countryEntityMapper::mapFromEntity))
                countryListCache.saveCountries(countryList)
            }
        }
    }

}
