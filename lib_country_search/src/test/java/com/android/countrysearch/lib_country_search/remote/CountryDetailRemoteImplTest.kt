package com.android.countrysearch.lib_country_search.remote

import com.android.countrysearch.lib_country_search.data.contract.remote.CountryDetailRemote
import com.android.countrysearch.lib_country_search.data.mapper.CountryDetailEntityMapper
import com.android.countrysearch.lib_country_search.data.mapper.CurrencyEntityMapper
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.remote.impl.CountryDetailRemoteImpl
import com.android.countrysearch.lib_country_search.remote.mapper.CountryDetailRemoteMapper
import com.android.countrysearch.lib_country_search.remote.mapper.CurrencyDetailRemoteMapper
import com.android.countrysearch.lib_country_search.remote.utils.COUNTRY_NAME
import com.android.countrysearch.lib_country_search.remote.utils.RequestDispatcher
import com.android.countrysearch.lib_country_search.remote.utils.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountryDetailRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var countryDetailRemote: CountryDetailRemote
    private val countryDetailRemoteMapper = CountryDetailRemoteMapper(CurrencyDetailRemoteMapper())
    private val countryDetailEntityMapper = CountryDetailEntityMapper(CurrencyEntityMapper())

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        countryDetailRemote = CountryDetailRemoteImpl(
            makeTestApiService(mockWebServer),
            countryDetailRemoteMapper,
            countryDetailEntityMapper
        )
    }

    @Test
    fun `check that fetchCountryDetail returns data`() = runBlocking {
        val country: CountryDetailEntity = countryDetailRemote.fetchCountryByName(COUNTRY_NAME)
        assertThat(country.name).isNotEmpty()
        assertThat(country.capitalName).isEmpty()
        assertThat(country.flag).isEqualTo("https://restcountries.eu/data/sen.svg")

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
