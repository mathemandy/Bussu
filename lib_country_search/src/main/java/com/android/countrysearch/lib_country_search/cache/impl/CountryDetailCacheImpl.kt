package com.android.countrysearch.lib_country_search.cache.impl

import com.android.countrysearch.cache.entity.CountryDetailCacheModel
import com.android.countrysearch.cache.room.CountryDetailDao
import com.android.countrysearch.lib_country_search.cache.mapper.CountryDetailCacheMapper
import com.android.countrysearch.lib_country_search.data.contract.cache.CountryDetailCache
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import javax.inject.Inject

internal class CountryDetailCacheImpl @Inject constructor(
    private val countryDetailDao: CountryDetailDao,
    private val countryDetailCacheMapper: CountryDetailCacheMapper
) : CountryDetailCache {

    override suspend fun saveCountry(countryDetailEntity: CountryDetailEntity) {
        val countryModel: CountryDetailCacheModel =
            countryDetailCacheMapper.mapToModel(countryDetailEntity)
        countryDetailDao.insertCountry(countryModel)
    }

    override suspend fun fetchCountry(countryUrl: String): CountryDetailEntity? {
        val model: CountryDetailCacheModel? = countryDetailDao.fetchCountry(countryUrl)
        return model?.let { countryDetailCacheMapper.mapToEntity(model) }
    }
}
