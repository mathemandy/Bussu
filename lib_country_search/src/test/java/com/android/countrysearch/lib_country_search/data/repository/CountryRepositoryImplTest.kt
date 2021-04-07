package com.android.countrysearch.lib_country_search.data.repository

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.fakes.FakeCountryListCache
import com.android.countrysearch.lib_country_search.data.fakes.FakeErrorSearchRemote
import com.android.countrysearch.lib_country_search.data.fakes.FakeSearchRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryEntityMapper
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.testutils.assertThrows
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.net.UnknownHostException

class CountryRepositoryImplTest {

    private val countryEntityMapper = CountryEntityMapper()

    private val countryRepository =
        SearchRepositoryImpl(FakeSearchRemote(), FakeCountryListCache(), countryEntityMapper)

    @Test
    fun `check that searchCountries returns data`() = runBlockingTest {
        val countries: List<Country> =
            countryRepository.searchCountries(DummyData.name).first()
        assertThat(countries).isNotEmpty()
    }

    @Test
    fun `check that searchCountries returns correct data`() = runBlockingTest {
        val country: Country =
            countryRepository.searchCountries(DummyData.name).first().first()
        assertThat(country.name).isEqualTo(DummyData.country.name)
        assertThat(country.flag).isEqualTo(DummyData.country.flag)
    }

    @Test(expected = UnknownHostException::class)
    fun `check that searchCountries throws error if network call fails`() = runBlockingTest {
        val errorRepository =
            SearchRepositoryImpl(FakeErrorSearchRemote(), FakeCountryListCache(),  countryEntityMapper)
        errorRepository.searchCountries(DummyData.name).first()
    }

    @Test
    fun `check that searchCountries returns error message if network call fails`() =
        runBlockingTest {
            val errorRepository =
                SearchRepositoryImpl(FakeErrorSearchRemote(), FakeCountryListCache(), countryEntityMapper)
            val throwable: UnknownHostException = assertThrows {
                errorRepository.searchCountries(DummyData.name).first()
            }
            assertThat(throwable).hasMessageThat().isEqualTo(FakeErrorSearchRemote.ERROR)
        }
}
