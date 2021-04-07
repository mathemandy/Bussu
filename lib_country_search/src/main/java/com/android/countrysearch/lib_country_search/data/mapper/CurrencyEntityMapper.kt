package com.android.countrysearch.lib_country_search.data.mapper

import com.android.countrysearch.lib_country_search.data.mapper.base.EntityMapper
import com.android.countrysearch.lib_country_search.data.model.CurrencyEntity
import com.android.countrysearch.lib_country_search.domain.model.CurrencyDetail
import com.android.countrysearch.lib_country_search.remote.model.response.Currency
import javax.inject.Inject

internal class CurrencyEntityMapper @Inject constructor() : EntityMapper<CurrencyEntity, CurrencyDetail> {

    override fun mapFromEntity(entity: CurrencyEntity): CurrencyDetail {
        return CurrencyDetail(
            code = entity.code,
            name = entity.name,
           symbol=  entity.symbol
        )
    }

    override fun mapToEntity(domain: CurrencyDetail): CurrencyEntity {
        return CurrencyEntity(
            code = domain.code,
            name = domain.name,
            symbol = domain.symbol
        )
    }
}
