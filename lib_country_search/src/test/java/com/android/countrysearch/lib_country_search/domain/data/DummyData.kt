package com.android.countrysearch.lib_country_search.domain.data

import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.model.CurrencyDetail

internal object DummyData {
    const val name = "Vanuatu"
    val country = Country(
        "Vanuatu",
        "c",
    )


    private val currencyDetail = CurrencyDetail(
        "VUV",
        "Vanuatu vatu",
        "Vt"
    )

    val countryDetail = CountryDetail(
        "Vanuatu",
        "Port Vila",
        listOf(currencyDetail),
        "currencyDetail"
    )


}
