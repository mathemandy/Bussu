package com.android.countrysearch.lib_country_search.cache.mapper

import com.android.countrysearch.cache.entity.CurrencyCacheModel
import com.android.countrysearch.lib_country_search.data.mapper.base.EntityMapper
import com.android.countrysearch.lib_country_search.data.model.CurrencyEntity
import javax.inject.Inject

internal class CurrencyCacheMapper @Inject constructor() :
    EntityMapper<CurrencyEntity, CurrencyCacheModel> {

    override fun mapFromEntity(entity: CurrencyEntity): CurrencyCacheModel {
        return CurrencyCacheModel(
            entity.code,
            entity.name,
            entity.symbol
        )
    }

    override fun mapToEntity(domain: CurrencyCacheModel): CurrencyEntity {
        return CurrencyEntity(
            domain.code,
            domain.name,
            domain.symbol
        )
    }
}
