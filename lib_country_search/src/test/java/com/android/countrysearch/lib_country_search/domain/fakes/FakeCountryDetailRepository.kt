package com.android.countrysearch.lib_country_search.domain.fakes

import com.android.countrysearch.lib_country_search.domain.data.DummyData
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.repository.CountryDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

internal class FakeCountryDetailRepository : CountryDetailRepository {

    private var countryDetailsFlow: Flow<CountryDetail> = flowOf(DummyData.countryDetail)

    var countryDetailsResponseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            countryDetailsFlow = makeCountryDetailResponse(value)
        }

    private fun makeCountryDetailResponse(type: ResponseType): Flow<CountryDetail> {
        return when (type) {
            ResponseType.DATA -> flowOf(DummyData.countryDetail)
            ResponseType.EMPTY -> flowOf()
            ResponseType.ERROR -> flow { throw SocketTimeoutException(FakeSearchRepository.ERROR_MSG) }
        }
    }

    override fun getCountryDetail(countryUrl: String): Flow<CountryDetail> {
        return countryDetailsFlow
    }

}
