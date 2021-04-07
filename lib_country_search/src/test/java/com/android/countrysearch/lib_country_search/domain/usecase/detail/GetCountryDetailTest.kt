package com.android.countrysearch.lib_country_search.domain.usecase.detail

import com.android.countrysearch.lib_country_search.domain.assertThrows
import com.android.countrysearch.lib_country_search.domain.data.DummyData
import com.android.countrysearch.lib_country_search.domain.exception.NoParamsException
import com.android.countrysearch.lib_country_search.domain.exception.noParamMessage
import com.android.countrysearch.lib_country_search.domain.executor.TestPostExecutionThread
import com.android.countrysearch.lib_country_search.domain.fakes.FakeCountryDetailRepository
import com.android.countrysearch.lib_country_search.domain.fakes.FakeSearchRepository
import com.android.countrysearch.lib_country_search.domain.fakes.ResponseType
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.net.SocketTimeoutException

class GetCountryDetailTest {

    private val repository = FakeCountryDetailRepository()

    private val fetchCountry = GetCountryDetail(
        repository,
        TestPostExecutionThread()
    )

    @Test
    fun `check that fetchCountry returns country detail`() = runBlockingTest {
        repository.countryDetailsResponseType = ResponseType.DATA
        val country: CountryDetail = fetchCountry(DummyData.countryDetail.name).first()
        assertThat(country).isEqualTo(DummyData.countryDetail)
    }

    @Test
    fun `check that fetchCountry returns error if call fails`() = runBlockingTest {
        repository.countryDetailsResponseType = ResponseType.ERROR
        val exception: SocketTimeoutException = assertThrows {
            fetchCountry(DummyData.countryDetail.name).collect()
        }
        assertThat(exception).hasMessageThat().isEqualTo(FakeSearchRepository.ERROR_MSG)
    }

    @Test
    fun `check that fetchCountry throws NoParams exception when called without params`() =
        runBlockingTest {
            val exception: NoParamsException = assertThrows {
                fetchCountry().collect()
            }
            assertThat(exception).hasMessageThat().isEqualTo(noParamMessage)
        }
}
