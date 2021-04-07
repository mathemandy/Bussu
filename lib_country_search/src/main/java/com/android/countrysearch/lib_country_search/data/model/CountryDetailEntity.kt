package com.android.countrysearch.lib_country_search.data.model

internal data class CountryDetailEntity(
    val name: String,
    val capitalName: String,
    val currencies: List<CurrencyEntity>,
    val flag: String,
)
