package com.android.countrysearch.lib_country_search.cache.mapper

import com.android.countrysearch.cache.base.CacheModelMapper
import com.android.countrysearch.cache.entity.CountryCacheModel
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import javax.inject.Inject

internal class CountryCacheModelMapper @Inject constructor() :
    CacheModelMapper<CountryCacheModel, CountryEntity> {

    @Throws(NumberFormatException::class)
    override fun mapToModel(entity: CountryEntity): CountryCacheModel {
        return CountryCacheModel(
            name = entity.name,
            flag = entity.flag,
        )
    }

    override fun mapToEntity(model: CountryCacheModel): CountryEntity {
        return CountryEntity(
            model.name,
            model.flag
        )
    }
}
