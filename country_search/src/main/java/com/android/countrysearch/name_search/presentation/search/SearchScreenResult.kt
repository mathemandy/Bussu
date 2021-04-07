package com.android.countrysearch.name_search.presentation.search

import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.presentation.mvi.base.ViewResult

sealed class SearchScreenResult : ViewResult {
    data class LoadedCountries(val searchHistory: List<Country>) : SearchScreenResult()
    object LoadingCountries : SearchScreenResult()
    data class EmptyCountry(val reason : String) : SearchScreenResult()

    sealed class SearchCountryResult : SearchScreenResult() {
        object Searching : SearchCountryResult()
        data class Error(val throwable: Throwable) : SearchCountryResult()
        data class Success(val countries: List<Country>) : SearchCountryResult()
    }
}
