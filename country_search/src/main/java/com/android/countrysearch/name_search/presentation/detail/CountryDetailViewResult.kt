package com.android.countrysearch.name_search.presentation.detail

import com.android.countrysearch.presentation.mvi.base.ViewResult

sealed class CountryDetailViewResult : ViewResult {
    data class CountryDetail(val countryDetail: com.android.countrysearch.lib_country_search.domain.model.CountryDetail) : CountryDetailViewResult()
    data class FetchCountryDetailError(
        val countryName: String,
        val error: Throwable
    ) : CountryDetailViewResult()
    object Loading : CountryDetailViewResult()

    object Retrying : CountryDetailViewResult()
}

