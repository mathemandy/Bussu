package com.android.countrysearch.name_search.fakes

import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.repository.CountryDetailRepository
import com.android.countrysearch.name_search.data.DummyData
import com.android.countrysearch.testutils.ERROR_MSG
import com.android.countrysearch.testutils.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

class FakeCountryDetailRepository : CountryDetailRepository {

    private var countryListFlow: Flow<CountryDetail> = flowOf(DummyData.countryDetail)

    var countryListResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            countryListFlow = makeCountriesResponse(value)
        }

    private fun makeCountriesResponse(type: ResponseType): Flow<CountryDetail> {
        return when (type) {
            ResponseType.DATA -> flowOf(DummyData.countryDetail)
            ResponseType.EMPTY -> flowOf()
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }



    override fun getCountryDetail(countryUrl: String): Flow<CountryDetail> {
        return countryListFlow
    }

}
