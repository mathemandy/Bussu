package com.android.countrysearch.lib_country_search.remote

import com.android.countrysearch.lib_country_search.data.contract.remote.SearchRemote
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.remote.impl.SearchRemoteImpl
import com.android.countrysearch.lib_country_search.remote.mapper.CountryRemoteModelMapper
import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import com.android.countrysearch.lib_country_search.remote.utils.NO_MATCH_SEARCH_QUERY
import com.android.countrysearch.lib_country_search.remote.utils.REQUEST_PATH
import com.android.countrysearch.lib_country_search.remote.utils.RequestDispatcher
import com.android.countrysearch.lib_country_search.remote.utils.SEARCH_QUERY
import com.android.countrysearch.lib_country_search.remote.utils.SEARCH_RESPONSE_PATH
import com.android.countrysearch.lib_country_search.remote.utils.adapter
import com.android.countrysearch.lib_country_search.remote.utils.getJson
import com.android.countrysearch.lib_country_search.remote.utils.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class SearchRemoteImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var searchRemote: SearchRemote
    private val countryRemoteModelMapper = CountryRemoteModelMapper()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = RequestDispatcher()
        mockWebServer.start()
        searchRemote =
            SearchRemoteImpl(makeTestApiService(mockWebServer), countryRemoteModelMapper)
    }

    @Test
    fun `check that searchCountries returns country list of same size`() = runBlocking {

        val countries: List<CountryEntity>? = searchRemote.searchCountries(SEARCH_QUERY)
        val responseSize: Int =
            getResponse(SEARCH_RESPONSE_PATH).size
        assertThat(countries).isNotEmpty()
        assertThat(countries?.size).isEqualTo(responseSize)
    }



    @Test
    fun `check that searchCountries returns correct data`() {
        runBlocking {
            val apiResponse: List<CountryRemoteModel> =
                getResponse(SEARCH_RESPONSE_PATH)
            val mappedResponse: List<CountryEntity> =
                countryRemoteModelMapper.mapModelList(apiResponse)
            val countryEntity: List<CountryEntity>? = searchRemote.searchCountries(SEARCH_QUERY)
            assertThat(mappedResponse).containsExactlyElementsIn(countryEntity)
        }
    }

    @Test
    fun `check that calling searchCountries makes request to correct path`() = runBlocking {
        searchRemote.searchCountries(SEARCH_QUERY)
        assertThat("$REQUEST_PATH$SEARCH_QUERY?fields=${getFields()}")
            .isEqualTo(mockWebServer.takeRequest().path?.replace("%3B", ";"))
    }

    @Test
    fun `check that calling searchCountries makes a GET request`() = runBlocking {
        searchRemote.searchCountries(SEARCH_QUERY)
        assertThat("GET $REQUEST_PATH$SEARCH_QUERY?fields=${getFields()} HTTP/1.1")
            .isEqualTo(mockWebServer.takeRequest().requestLine.replace("%3B", ";"))
    }
    fun getFields(): String{
        return "name;flag;currencies;capital"
    }

    private fun getResponse(responsePath: String): List<CountryRemoteModel> {
        val toJson = getJson(responsePath.trim())
        val fromJson =  adapter.fromJson(toJson)!!
        return  fromJson
    }



    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
