package com.android.countrysearch.lib_country_search.data.fakes

import com.android.countrysearch.lib_country_search.data.contract.cache.CountryDetailCache
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity

internal class FakeCountryDetailCache : CountryDetailCache {

    private val cache = LinkedHashMap<String, CountryDetailEntity>()

    override suspend fun saveCountry(countryDetailEntity: CountryDetailEntity) {
        cache[countryDetailEntity.name] = countryDetailEntity
    }

    override suspend fun fetchCountry(name: String): CountryDetailEntity? {
        return cache[name]
    }
}
