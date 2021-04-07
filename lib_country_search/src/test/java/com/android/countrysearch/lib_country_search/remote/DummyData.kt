package com.android.countrysearch.lib_country_search.remote

import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import com.android.countrysearch.lib_country_search.remote.model.response.Currency

internal object DummyData {


    val currency  = Currency(
        "BRL",
        "Brazilian real",
        "R\$"
    )

    val countryRemoteModel = CountryRemoteModel(
        "Brazil",
        "Brasília",
        listOf(currency),
        "https://restcountries.eu/data/bra.svg"
    )
}
