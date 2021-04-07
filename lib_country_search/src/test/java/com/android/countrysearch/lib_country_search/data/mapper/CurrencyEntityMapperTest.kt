package com.android.countrysearch.lib_country_search.data.mapper

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.model.CurrencyEntity
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CurrencyEntityMapperTest {

    private val mapper = CurrencyEntityMapper()

    @Test
    fun mapFromEntity() {
        val entity: CurrencyEntity = DummyData.currencyEntity
        val domain = mapper.mapFromEntity(entity)
        assertThat(entity.code).isEqualTo(domain.code)
        assertThat(entity.name).isEqualTo(domain.name)
        assertThat(entity.symbol).isEqualTo(domain.symbol)
    }

    @Test
    fun mapToEntity() {
        val domain = DummyData.currencyDetail
        val entity: CurrencyEntity = mapper.mapToEntity(domain)
        assertThat(domain.code).isEqualTo(entity.code)
        assertThat(domain.symbol).isEqualTo(entity.symbol)
        assertThat(domain.name).isEqualTo(entity.name)
    }
}
