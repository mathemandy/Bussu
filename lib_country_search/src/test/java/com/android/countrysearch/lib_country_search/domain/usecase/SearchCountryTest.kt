package com.android.countrysearch.lib_country_search.domain.usecase

import com.android.countrysearch.lib_country_search.domain.assertThrows
import com.android.countrysearch.lib_country_search.domain.data.DummyData
import com.android.countrysearch.lib_country_search.domain.exception.NoParamsException
import com.android.countrysearch.lib_country_search.domain.exception.noParamMessage
import com.android.countrysearch.lib_country_search.domain.executor.TestPostExecutionThread
import com.android.countrysearch.lib_country_search.domain.fakes.FakeSearchRepository
import com.android.countrysearch.lib_country_search.domain.fakes.FakeSearchRepository.Companion.ERROR_MSG
import com.android.countrysearch.lib_country_search.domain.fakes.ResponseType
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.usecase.search.SearchCountry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.net.SocketTimeoutException

class SearchCountryTest {

    private val fakeSearchRepository = FakeSearchRepository()

    private val searchCountry =
        SearchCountry(fakeSearchRepository, TestPostExecutionThread())

    @Test
    fun `check that calling searchCountries returns list of countries`() = runBlockingTest {
        fakeSearchRepository.responseType = ResponseType.DATA
        val countries: List<Country> = searchCountry(DummyData.name).first()
        assertThat(countries.size).isAtLeast(1)
    }

    @Test
    fun `check that calling searchCountries returns correct data`() = runBlockingTest {
        fakeSearchRepository.responseType = ResponseType.DATA
        val countries: List<Country> = searchCountry(DummyData.name).first()
        val country: Country = countries.first()
        assertThat(country.name).isEqualTo(DummyData.country.name)
        assertThat(country.flag).isEqualTo(DummyData.country.flag)

    }

    @Test
    fun `check that calling searchCountries returns empty list if response is empty`() =
        runBlockingTest {
            fakeSearchRepository.responseType = ResponseType.EMPTY
            val countries: List<Country> = searchCountry(DummyData.name).first()
            assertThat(countries).isEmpty()
        }

    @Test
    fun `check that calling searchCountries returns error if call fails`() = runBlockingTest {
        fakeSearchRepository.responseType = ResponseType.ERROR
        val exception: SocketTimeoutException = assertThrows {
            searchCountry(DummyData.name).collect()
        }
        assertThat(exception).hasMessageThat().isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that calling searchCountries without name throws NoParamException`() =
        runBlockingTest {
            val exception: NoParamsException = assertThrows {
                searchCountry().collect()
            }
            assertThat(exception).hasMessageThat().isEqualTo(noParamMessage)
        }
}
