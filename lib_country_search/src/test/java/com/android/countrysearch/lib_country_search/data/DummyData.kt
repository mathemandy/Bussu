package com.android.countrysearch.lib_country_search.data

import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.data.model.CurrencyEntity
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.model.CurrencyDetail

internal object DummyData {

    const val name: String = "Zimbabwe"

    val countryEntity = CountryEntity(
        "Zimbabwe",
        "https://restcountries.eu/data/zwe.svg",
    )
    val country = Country(
        "Zimbabwe",
        "https://restcountries.eu/data/zwe.svg",
    )

    val currencyEntity = CurrencyEntity(
        "ZMW",
        "Zambian kwacha",
        "ZK"
    )

    val countryDetailEntity = CountryDetailEntity(
        "Zambia",
        "Lusaka",
        listOf(currencyEntity),
        "https://restcountries.eu/data/zmb.svg"
    )

    val currencyDetail = CurrencyDetail(
        "BWP",
        "Botswana pula",
        "P"
    )

    val countryDetail = CountryDetail(
        "Zimbabwe",
        "Harare",
        listOf(currencyDetail),
        "https://restcountries.eu/data/zwe.svg"
    )



}
