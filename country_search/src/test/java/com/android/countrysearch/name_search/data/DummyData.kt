package com.android.countrysearch.name_search.data

import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.model.CurrencyDetail
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.model.CurrencyModel
import com.android.countrysearch.testutils.ERROR_MSG
import java.net.SocketTimeoutException

internal object DummyData {

    val currencyEntity = CurrencyModel(
        "ZMW",
        "Zambian kwacha",
        "ZK"
    )

    val countryModel = CountryModel(
        "Zambia",
        "Lusaka",
        listOf(currencyEntity),
        "https://restcountries.eu/data/zmb.svg"
    )


    val country = Country(
        "Zimbabwe",
        "https://restcountries.eu/data/zwe.svg",
    )

    val countryList: List<Country> = listOf(country)

    const val query = "Botswana"

    val currencyDetail = CurrencyDetail(
        "BWP",
        "Botswana pula",
        "P"
    )

    val countryDetail = CountryDetail(
        "Botswana",
        "Gaborone",
        listOf(currencyDetail),
        "https://restcountries.eu/data/zmb.svg"
    )




    val exception: SocketTimeoutException
        get() = SocketTimeoutException(ERROR_MSG)
}
