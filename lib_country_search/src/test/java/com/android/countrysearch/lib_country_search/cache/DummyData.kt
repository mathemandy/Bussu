package com.android.countrysearch.lib_country_search.cache

import com.android.countrysearch.cache.entity.CountryCacheModel
import com.android.countrysearch.cache.entity.CountryDetailCacheModel
import com.android.countrysearch.cache.entity.CurrencyCacheModel
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.data.model.CurrencyEntity

internal object DummyData {
    val entity = CountryEntity(
        "Afghanistan",
        "https://restcountries.eu/data/afg.svg"
    )

    val countryCacheModel = CountryCacheModel(
        "Mariehamn",
        "https://restcountries.eu/data/ala.svg"
    )

    val currency = CurrencyEntity(
"AFN",
        "Afghan afghani",
        "؋"
    )


    val countryDetailEntity = CountryDetailEntity(
       "Afghanistan",
        "Kabul",
        listOf(currency),
        "https://restcountries.eu/data/afg.svg"
    )

    val currencyCacheModel = CurrencyCacheModel(
        "EUR",
        "Euro",
        "€"
    )


    val countryDetailCacheModel = CountryDetailCacheModel(
        "Åland Islands",
        "Mariehamn",
        listOf(currencyCacheModel),
        "https://restcountries.eu/data/ala.svg"
    )


}
