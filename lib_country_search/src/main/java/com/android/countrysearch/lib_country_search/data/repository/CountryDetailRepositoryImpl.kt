package com.android.countrysearch.lib_country_search.data.repository

import com.android.countrysearch.lib_country_search.data.contract.cache.CountryDetailCache
import com.android.countrysearch.lib_country_search.data.contract.remote.CountryDetailRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryDetailEntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.repository.CountryDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class CountryDetailRepositoryImpl @Inject constructor(
    private val countryDetailRemote: CountryDetailRemote,
    private val countryDetailCache: CountryDetailCache,
    private val countryDetailEntityMapper: CountryDetailEntityMapper,
) : CountryDetailRepository {

    override fun getCountryDetail(name: String): Flow<CountryDetail> {
        return flow {
            val cachedCountry: CountryDetailEntity? =
                countryDetailCache.fetchCountry(name)
            if (cachedCountry != null) {
                emit(countryDetailEntityMapper.mapFromEntity(cachedCountry))
            } else {
                val countryDetail: CountryDetailEntity =
                    countryDetailRemote.fetchCountryByName(name)
                emit(countryDetailEntityMapper.mapFromEntity(countryDetail))
                countryDetailCache.saveCountry(countryDetail)
            }
        }
    }

}
