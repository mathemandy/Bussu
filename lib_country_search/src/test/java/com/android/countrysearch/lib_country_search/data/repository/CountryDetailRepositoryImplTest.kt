package com.android.countrysearch.lib_country_search.data.repository

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.fakes.FakeCountryDetailCache
import com.android.countrysearch.lib_country_search.data.fakes.FakeCountryDetailRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryDetailEntityMapper
import com.android.countrysearch.lib_country_search.data.mapper.CurrencyEntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class CountryDetailRepositoryImplTest {

    private val fakeCountryDetailCache = FakeCountryDetailCache()
    private val currencyEntityMapper    = CurrencyEntityMapper()
    private val countryDetailEntityMapper = CountryDetailEntityMapper(currencyEntityMapper)

    private val repository = CountryDetailRepositoryImpl(
        FakeCountryDetailRemote(),
        fakeCountryDetailCache,
        countryDetailEntityMapper
    )

    @Test
    fun `check that getCountryDetail returns data from remote when cache is empty`() =
        runBlockingTest {
            val countryDetailEntity: CountryDetailEntity = DummyData.countryDetailEntity
            val country: Flow<CountryDetail> =
                repository.getCountryDetail(countryDetailEntity.name)
            assertThat(countryDetailEntity).isEqualTo(
                countryDetailEntityMapper.mapToEntity(country.first())
            )
        }

    @Test
    fun `check that getCountryDetail returns data from cache`() = runBlockingTest {
        val countryDetailEntity1: CountryDetailEntity = DummyData.countryDetailEntity
        val countryDetailEntity2: CountryDetailEntity =
            countryDetailEntity1.copy(currencies = emptyList(), name = "Brazil")
        fakeCountryDetailCache.saveCountry(countryDetailEntity2)
        val country: CountryDetail =
            repository.getCountryDetail(countryDetailEntity2.name).first()
        assertThat(countryDetailEntity2).isEqualTo(
            countryDetailEntityMapper.mapToEntity(
                country
            )
        )
        assertThat(countryDetailEntity1).isNotEqualTo(
            countryDetailEntityMapper.mapToEntity(
                country
            )
        )
    }
}
