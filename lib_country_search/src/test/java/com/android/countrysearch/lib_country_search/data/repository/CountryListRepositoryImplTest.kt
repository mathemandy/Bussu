package com.android.countrysearch.lib_country_search.data.repository

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.fakes.FakeCountryListCache
import com.android.countrysearch.lib_country_search.data.fakes.FakeCountryListRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryEntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class CountryListRepositoryImplTest {

    private val countryEntityMapper = CountryEntityMapper()
    private val fakeCountryCache = FakeCountryListCache()

    private val countryListRepository =
        CountryListRepositoryImpl(fakeCountryCache,
            FakeCountryListRemote(),
            countryEntityMapper)

    @Test
    fun `check that getCountry List returns data from remote when cache is empty`() =
        runBlockingTest {
            val countryListEntity: List<CountryEntity> = listOf(DummyData.countryEntity)
            val country: Flow<List<Country>> =
                countryListRepository.getCountryList()
            assertThat(countryListEntity.first()).isEqualTo(
                countryEntityMapper.mapToEntity(country.first().first())
            )
        }


    @Test
    fun `check that getCountry List returns data from cache`() = runBlockingTest {
        val country1: CountryEntity = DummyData.countryEntity
        val country2: CountryEntity =
            country1.copy(flag = "https://restcountries.eu/data/bra.svg", name = "Brazil")
        fakeCountryCache.saveCountry(country2)
        val country: Country =
            countryListRepository.getCountryList().first().first()
        assertThat(country2).isEqualTo(
            countryEntityMapper.mapToEntity(
                country
            )
        )
        assertThat(country1).isNotEqualTo(
            countryEntityMapper.mapToEntity(
                country
            )
        )
    }



}
