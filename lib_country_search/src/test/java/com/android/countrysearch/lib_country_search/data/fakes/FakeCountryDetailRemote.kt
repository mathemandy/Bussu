package com.android.countrysearch.lib_country_search.data.fakes

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.contract.remote.CountryDetailRemote
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity

internal class FakeCountryDetailRemote : CountryDetailRemote {

    override suspend fun fetchCountryByName(countryUrl: String): CountryDetailEntity {
        return DummyData.countryDetailEntity
    }

}
