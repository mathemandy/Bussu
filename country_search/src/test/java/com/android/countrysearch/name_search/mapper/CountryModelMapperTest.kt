package com.android.countrysearch.name_search.mapper

import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.name_search.data.DummyData
import com.android.countrysearch.name_search.model.CountryModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryModelMapperTest {

    private val countryModelMapper = CountryModelMapper()

    @Test
    fun `check that mapToModel returns correct data`() {
        val country: Country = DummyData.country
        val model: CountryModel = countryModelMapper.mapToModel(country)
        assertThat(country.name).isEqualTo(model.name)
        assertThat(country.flag).isEqualTo(model.flag)
    }

    @Test
    fun `check that mapToDomain returns correct data`() {
        val model: CountryModel = DummyData.countryModel
        val countryDomain: Country = countryModelMapper.mapToDomain(model)
        assertThat(model.name).isEqualTo(countryDomain.name)
        assertThat(model.flag).isEqualTo(countryDomain.flag)


    }
}
