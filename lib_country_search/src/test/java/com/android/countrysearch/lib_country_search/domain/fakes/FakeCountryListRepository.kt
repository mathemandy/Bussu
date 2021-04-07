package com.android.countrysearch.lib_country_search.domain.fakes

import com.android.countrysearch.lib_country_search.domain.data.DummyData
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.repository.CountryListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

internal class FakeCountryListRepository : CountryListRepository {

    private var countryListFlow: Flow<List<Country>> = flowOf(listOf(DummyData.country))


    var countryListResponseType: ResponseType = ResponseType.DATA
    set(value) {
        field = value
        countryListFlow = makeCountryListResponse(value)
    }

    private fun makeCountryListResponse(type: ResponseType): Flow<List<Country>> {
        return when (type) {
            ResponseType.DATA -> flowOf(listOf(DummyData.country))
            ResponseType.EMPTY -> flowOf()
            ResponseType.ERROR -> flow { throw SocketTimeoutException(FakeSearchRepository.ERROR_MSG) }
        }
    }


    override fun getCountryList(): Flow<List<Country>> {
        return countryListFlow
    }

}
