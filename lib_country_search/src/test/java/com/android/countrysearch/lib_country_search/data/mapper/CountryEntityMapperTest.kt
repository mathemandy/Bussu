package com.android.countrysearch.lib_country_search.data.mapper

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryEntityMapperTest {

    private val countryEntityMapper = CountryEntityMapper()

    @Test
    fun `check that mapFromEntity maps data correctly`() {
        val countryEntity: CountryEntity = DummyData.countryEntity
        val countryDomain: Country = countryEntityMapper.mapFromEntity(countryEntity)
        assertThat(countryEntity.name).isEqualTo(countryDomain.name)
        assertThat(countryEntity.flag).isEqualTo(countryDomain.flag)
    }

    @Test
    fun `check that mapToEntity maps data correctly`() {
        val country: Country = DummyData.country
        val countryEntity: CountryEntity = countryEntityMapper.mapToEntity(country)
        assertThat(countryEntity.name).isEqualTo(country.name)
        assertThat(countryEntity.flag).isEqualTo(country.flag)
    }
}
