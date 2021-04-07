package com.android.countrysearch.lib_country_search.cache.mapper

import com.android.countrysearch.cache.entity.CountryCacheModel
import com.android.countrysearch.lib_country_search.cache.DummyData
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryCacheModelMapperTest {

    private val countryCacheModelMapper =
        CountryCacheModelMapper()

    @Test
    fun `check that mapToModel returns correct data`() {
        val entity: CountryEntity = DummyData.entity
        val model: CountryCacheModel = countryCacheModelMapper.mapToModel(entity)
        assertThat(entity.name).isEqualTo(model.name)
        assertThat(entity.flag).isEqualTo(model.flag)

    }

    @Test
    fun `check that mapToEntity returns correct data`() {
        val model: CountryCacheModel = DummyData.countryCacheModel
        val entity: CountryEntity = countryCacheModelMapper.mapToEntity(model)
        assertThat(model.name).isEqualTo(entity.name)
        assertThat(model.flag).isEqualTo(entity.flag)
    }
}
