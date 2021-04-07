package com.android.countrysearch.lib_country_search.remote.model.response

internal data class CountryRemoteModel(
    val name: String?,
    val capital: String?  = "",
    val currencies: List<Currency>? = emptyList(),
    val flag: String? = "",
)
