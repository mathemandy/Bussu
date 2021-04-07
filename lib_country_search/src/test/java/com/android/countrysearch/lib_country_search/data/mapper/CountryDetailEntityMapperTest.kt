package com.android.countrysearch.lib_country_search.data.mapper

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryDetailEntityMapperTest {


    private val mapper = CountryDetailEntityMapper(CurrencyEntityMapper())
    @Test
    fun mapFromEntity() {
        val entity: CountryDetailEntity = DummyData.countryDetailEntity
        val domain: CountryDetail = mapper.mapFromEntity(entity)
        assertThat(entity.name).isEqualTo(domain.name)
        assertThat(entity.currencies.first().name).isEqualTo(domain.currencies.first().name)
        assertThat(entity.currencies.first().symbol).isEqualTo(domain.currencies.first().symbol)
        assertThat(entity.currencies.first().code).isEqualTo(domain.currencies.first().code)
        assertThat(entity.flag).isEqualTo(domain.flag)
        assertThat(entity.capitalName).isEqualTo(domain.capitalName)
    }

    @Test
    fun mapToEntity() {
        val domain: CountryDetail = DummyData.countryDetail
        val entity: CountryDetailEntity = mapper.mapToEntity(domain)
        assertThat(domain.name).isEqualTo(entity.name)
        assertThat(entity.currencies.first().name).isEqualTo(domain.currencies.first().name)
        assertThat(entity.currencies.first().symbol).isEqualTo(domain.currencies.first().symbol)
        assertThat(entity.currencies.first().code).isEqualTo(domain.currencies.first().code)
        assertThat(domain.flag).isEqualTo(entity.flag)
        assertThat(domain.capitalName).isEqualTo(entity.capitalName)
    }
}
