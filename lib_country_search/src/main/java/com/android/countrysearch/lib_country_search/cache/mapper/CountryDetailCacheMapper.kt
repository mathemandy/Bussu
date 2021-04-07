package com.android.countrysearch.lib_country_search.cache.mapper

import com.android.countrysearch.cache.base.CacheModelMapper
import com.android.countrysearch.cache.entity.CountryDetailCacheModel
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import javax.inject.Inject

internal class CountryDetailCacheMapper @Inject constructor(
    private val currencyCacheMapper: CurrencyCacheMapper
) :
    CacheModelMapper<CountryDetailCacheModel, CountryDetailEntity> {

    override fun mapToModel(entity: CountryDetailEntity): CountryDetailCacheModel {
        return CountryDetailCacheModel(
            name = entity.name,
            flag = entity.flag,
            currencies = currencyCacheMapper.mapFromEntityList(entity.currencies),
            capitalName = entity.capitalName
        )
    }

    override fun mapToEntity(model: CountryDetailCacheModel): CountryDetailEntity {
        return CountryDetailEntity(
           name = model.name,
           flag = model.flag,
           currencies=  currencyCacheMapper.mapFromDomainList(model.currencies),
            capitalName = model.capitalName
        )
    }
}
