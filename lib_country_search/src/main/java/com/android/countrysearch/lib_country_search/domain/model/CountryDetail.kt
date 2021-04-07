package com.android.countrysearch.lib_country_search.domain.model

data class CountryDetail(
    val name: String,
    val capitalName: String,
    val currencies: List<CurrencyDetail>,
    val flag: String,
)
