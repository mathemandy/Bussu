package com.android.countrysearch.lib_country_search.domain.fakes

import com.android.countrysearch.lib_country_search.domain.data.DummyData
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.net.SocketTimeoutException

internal class FakeSearchRepository : SearchRepository {

    companion object {
        const val ERROR_MSG: String = "No network"
    }

    private var countriesFlow: Flow<List<Country>> = flowOf(listOf(DummyData.country))

    var responseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            countriesFlow = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Flow<List<Country>> {
        return when (type) {
            ResponseType.DATA -> flowOf(listOf(DummyData.country))
            ResponseType.EMPTY -> flowOf(listOf())
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }

    override fun searchCountries(countryName: String): Flow<List<Country>> {
        return countriesFlow
    }
}

internal enum class ResponseType {
    DATA,
    EMPTY,
    ERROR
}
