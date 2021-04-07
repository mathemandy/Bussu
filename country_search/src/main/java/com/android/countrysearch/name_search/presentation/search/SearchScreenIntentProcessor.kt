package com.android.countrysearch.name_search.presentation.search

import com.android.countrysearch.core.ext.errorMessage
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.usecase.search.SearchCountry
import com.android.countrysearch.lib_country_search.domain.usecase.countrylist.GetCountryList
import com.android.countrysearch.name_search.mapper.CountryModelMapper
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.presentation.SearchIntentProcessor
import com.android.countrysearch.name_search.presentation.search.SearchScreenResult.SearchCountryResult
import com.android.countrysearch.name_search.views.search.RetryFetchCountryIntent
import com.android.countrysearch.presentation.mvi.base.InvalidViewIntentException
import com.android.countrysearch.presentation.mvi.base.ViewIntent
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchScreenIntentProcessor @Inject constructor(
    private val searchCountry: SearchCountry,
    private val getCountryList: GetCountryList,
    private val modelMapper: CountryModelMapper
) : SearchIntentProcessor {

    override fun intentToResult(viewIntent: ViewIntent): Flow<SearchScreenResult> {
        return when (viewIntent) {
            is SearchIntent -> executeSearch(viewIntent.query)
            RetryFetchCountryIntent -> loadCountryList()
            LoadSearchHistory -> loadCountryList()
            is RetrySearchIntent -> executeSearch(viewIntent.query)
            else -> throw InvalidViewIntentException(viewIntent)
        }
    }


    private fun loadCountryList(): Flow<SearchScreenResult> {
        return getCountryList()
            .map { countries ->
                if (countries.isNotEmpty()){
                    SearchScreenResult.LoadedCountries(countries)
                }else {
                    SearchScreenResult.EmptyCountry("")
                }

            }.onStart {
                emit(SearchScreenResult.LoadingCountries)
            }
            .catch { error ->
                emit(SearchScreenResult.EmptyCountry(error.errorMessage))
            }
    }

    private fun executeSearch(query: String): Flow<SearchScreenResult> {
        if (query.isBlank()) {
            return loadCountryList()
        }
        return searchCountry(query.trim())
            .map<List<Country>, SearchCountryResult> { countries ->
                SearchCountryResult.Success(countries)
            }.onStart {
                emit(SearchCountryResult.Searching)
            }.catch { throwable ->
                emit(SearchCountryResult.Error(throwable))
            }
    }
}
