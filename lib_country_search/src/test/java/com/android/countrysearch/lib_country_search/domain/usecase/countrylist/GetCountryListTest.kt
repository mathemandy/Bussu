package com.android.countrysearch.lib_country_search.domain.usecase.countrylist

import com.android.countrysearch.lib_country_search.domain.assertThrows
import com.android.countrysearch.lib_country_search.domain.data.DummyData
import com.android.countrysearch.lib_country_search.domain.executor.TestPostExecutionThread
import com.android.countrysearch.lib_country_search.domain.fakes.FakeCountryListRepository
import com.android.countrysearch.lib_country_search.domain.fakes.FakeSearchRepository
import com.android.countrysearch.lib_country_search.domain.fakes.ResponseType
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.net.SocketTimeoutException

class GetCountryListTest {

    private val countryListRepository = FakeCountryListRepository()
    private val getCountryList =
        GetCountryList(countryListRepository, TestPostExecutionThread())

    @Test
    fun `check that getCountryList returns list of countries`() = runBlockingTest {
        countryListRepository.countryListResponseType = ResponseType.DATA
        val countries: List<Country> = getCountryList().first()
        assertThat(countries.first()).isEqualTo(DummyData.country)
    }


    @Test
    fun `check that fetchCountry returns error if call fails`() = runBlockingTest {
        countryListRepository.countryListResponseType = ResponseType.ERROR
        val exception: SocketTimeoutException = assertThrows {
            getCountryList().collect()
        }
        assertThat(exception).hasMessageThat().isEqualTo(FakeSearchRepository.ERROR_MSG)
    }
}
