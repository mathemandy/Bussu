package com.android.countrysearch.lib_country_search.data.mapper

import com.android.countrysearch.lib_country_search.data.mapper.base.EntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import javax.inject.Inject

internal class CountryDetailEntityMapper @Inject constructor(private val currencyEntityMapper: CurrencyEntityMapper) :
    EntityMapper<CountryDetailEntity, CountryDetail> {

    override fun mapFromEntity(entity: CountryDetailEntity): CountryDetail {
        return CountryDetail(
            name = entity.name,
            capitalName = entity.capitalName,
            currencies = currencyEntityMapper.mapFromEntityList(entity.currencies),
            flag = entity.flag
        )
    }

    override fun mapToEntity(domain: CountryDetail): CountryDetailEntity {
        return CountryDetailEntity(
            name = domain.name,
            capitalName = domain.capitalName,
            currencies = currencyEntityMapper.mapFromDomainList( domain.currencies),
            flag = domain.flag
        )
    }
}
