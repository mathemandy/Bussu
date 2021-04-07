package com.android.countrysearch.lib_country_search.data.mapper

import com.android.countrysearch.lib_country_search.data.mapper.base.EntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import javax.inject.Inject

internal class CountryEntityMapper @Inject constructor() :
    EntityMapper<CountryEntity, Country> {

    override fun mapFromEntity(entity: CountryEntity): Country {
        return Country(
            entity.name,
            entity.flag
        )
    }

    override fun mapToEntity(domain: Country): CountryEntity {
        return CountryEntity(
            domain.name,
            domain.flag
        )
    }
}
