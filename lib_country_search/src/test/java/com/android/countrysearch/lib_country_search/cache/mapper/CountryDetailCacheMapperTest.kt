package com.android.countrysearch.lib_country_search.cache.mapper

import com.android.countrysearch.cache.entity.CountryDetailCacheModel
import com.android.countrysearch.lib_country_search.cache.DummyData
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryDetailCacheMapperTest {

    private val mapper = CountryDetailCacheMapper(CurrencyCacheMapper())

    @Test
    fun mapToModel() {
        val entity: CountryDetailEntity = DummyData.countryDetailEntity
        val model: CountryDetailCacheModel = mapper.mapToModel(entity)
        assertThat(entity.name).isEqualTo(model.name)
        assertThat(entity.flag).isEqualTo(model.flag)
        assertThat(entity.currencies.first().name).isEqualTo(model.currencies.first().name)
        assertThat(entity.currencies.first().symbol).isEqualTo(model.currencies.first().symbol)
        assertThat(entity.currencies.first().code).isEqualTo(model.currencies.first().code)
        assertThat(entity.capitalName).isEqualTo(model.capitalName)
    }

    @Test
    fun mapToEntity() {
        val model: CountryDetailCacheModel = DummyData.countryDetailCacheModel
        val entity: CountryDetailEntity = mapper.mapToEntity(model)
        assertThat(model.name).isEqualTo(entity.name)
        assertThat(model.flag).isEqualTo(entity.flag)
        assertThat(entity.currencies.first().name).isEqualTo(model.currencies.first().name)
        assertThat(entity.currencies.first().symbol).isEqualTo(model.currencies.first().symbol)
        assertThat(entity.currencies.first().code).isEqualTo(model.currencies.first().code)
        assertThat(model.capitalName).isEqualTo(entity.capitalName)
    }
}
