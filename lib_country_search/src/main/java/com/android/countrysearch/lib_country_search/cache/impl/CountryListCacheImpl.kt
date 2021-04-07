package com.android.countrysearch.lib_country_search.cache.impl

import com.android.countrysearch.cache.entity.CountryCacheModel
import com.android.countrysearch.cache.room.CountryDetailDao
import com.android.countrysearch.cache.room.CountryListDao
import com.android.countrysearch.lib_country_search.cache.mapper.CountryCacheModelMapper
import com.android.countrysearch.lib_country_search.data.contract.cache.CountryListCache
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.domain.model.Country
import javax.inject.Inject

internal class CountryListCacheImpl @Inject constructor(
    private val countryListDao: CountryListDao,
    private val countryDetailDao: CountryDetailDao,
    private val countryCacheModelMapper: CountryCacheModelMapper
) : CountryListCache {

    override suspend fun saveCountry(country: CountryEntity) {
        val countryModel: CountryCacheModel = countryCacheModelMapper.mapToModel(country)
        countryListDao.insertCountry(countryModel)
    }

    override suspend fun getSavedCountries(): List<CountryEntity> {
        val countryModels: List<CountryCacheModel> = countryListDao.countryList()
        return countryModels.map(countryCacheModelMapper::mapToEntity)
    }

    override suspend fun clearCountries() {
        countryListDao.clearHistory()
        countryDetailDao.clearData()
    }

    override suspend fun getCountryByName(name: String): List<CountryEntity> {
        val countryModels: List<CountryCacheModel> = countryListDao.fetchCountry("${name}%")
        return countryModels.map(countryCacheModelMapper::mapToEntity)
    }

    override suspend fun saveCountries(country: List<CountryEntity>) {
        country.map {
            val countryModel: CountryCacheModel = countryCacheModelMapper.mapToModel(it)
            countryListDao.insertCountry(countryModel)
        }

    }
}
