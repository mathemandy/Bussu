package com.android.countrysearch.lib_country_search.data.fakes

import com.android.countrysearch.lib_country_search.data.contract.cache.CountryListCache
import com.android.countrysearch.lib_country_search.data.model.CountryEntity

internal class FakeCountryListCache : CountryListCache {

    private val cache = LinkedHashMap<String, CountryEntity>()

    override suspend fun saveCountry(country: CountryEntity) {
        cache[country.name] = country
    }

    override suspend fun saveCountries(countries: List<CountryEntity>) {
        countries.forEach {
            cache[it.name] = it
        }
    }

    override suspend fun getSavedCountries(): List<CountryEntity> {
        return cache.values.toList().reversed()
    }

    override suspend fun getCountryByName(name: String): List<CountryEntity> {
        val countries = mutableListOf<CountryEntity>()
        cache.keys.forEach {
             if (it.startsWith(name)){ cache.get(it)?.let { it1 -> countries.add(it1) }
             }
         }
        return countries
    }

    override suspend fun clearCountries() {
        cache.clear()
    }
}
