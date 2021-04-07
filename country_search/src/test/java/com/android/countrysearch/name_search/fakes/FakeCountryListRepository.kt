package com.android.countrysearch.name_search.fakes

import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.repository.CountryListRepository
import com.android.countrysearch.name_search.data.DummyData
import com.android.countrysearch.testutils.ERROR_MSG
import com.android.countrysearch.testutils.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

class FakeCountryListRepository : CountryListRepository {

    private var countriesList: Flow<List<Country>> =
        flowOf(DummyData.countryList)

    var responseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            countriesList = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Flow<List<Country>> {
        return when (type) {
            ResponseType.DATA -> flowOf(listOf(DummyData.country))
            ResponseType.EMPTY -> flowOf(listOf())
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }

    override fun getCountryList(): Flow<List<Country>> {
        return countriesList
    }
}
