package com.android.countrysearch.lib_country_search.data.fakes

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.contract.remote.SearchRemote
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import java.net.UnknownHostException

internal class FakeSearchRemote : SearchRemote {

    override suspend fun searchCountries(name: String): List<CountryEntity> {
        return listOf(DummyData.countryEntity)
    }
}

internal class FakeErrorSearchRemote : SearchRemote {

    override suspend fun searchCountries(name: String): List<CountryEntity>? {
        throw UnknownHostException(ERROR)
    }

    companion object {
        const val ERROR: String = "Cannot resolve host `https://restcountries.eu`"
    }
}
