package com.android.countrysearch.lib_country_search.data.fakes

import com.android.countrysearch.lib_country_search.data.DummyData
import com.android.countrysearch.lib_country_search.data.contract.cache.CountryListCache
import com.android.countrysearch.lib_country_search.data.contract.remote.CountryRemote
import com.android.countrysearch.lib_country_search.data.model.CountryEntity

internal class FakeCountryListRemote : CountryRemote {


    override suspend fun fetchCountries(): List<CountryEntity> {
        return listOf(DummyData.countryEntity)
    }
}
