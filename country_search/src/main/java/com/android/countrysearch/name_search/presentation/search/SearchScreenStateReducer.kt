package com.android.countrysearch.name_search.presentation.search

import com.android.countrysearch.core.ext.errorMessage
import com.android.countrysearch.name_search.mapper.CountryModelMapper
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.presentation.SearchStateReducer
import com.android.countrysearch.name_search.presentation.search.SearchScreenResult.SearchCountryResult
import javax.inject.Inject

class SearchScreenStateReducer @Inject constructor(
    private val countryModelMapper: CountryModelMapper
) : SearchStateReducer {

    override fun reduce(
        oldState: SearchScreenState,
        result: SearchScreenResult
    ): SearchScreenState {
        return when (result) {
            is SearchScreenResult.LoadedCountries -> oldState.translateTo {
                countryListState {
                    DataLoaded(countryModelMapper.mapToModelList(result.searchHistory))
                }
            }
            is SearchScreenResult.EmptyCountry -> oldState.translateTo {
                countryListState {
                    Empty("")
                }
            }
            is SearchScreenResult.LoadingCountries  ->  oldState.translateTo {
                countryListState {
                    Loading()
                }
            }
            SearchCountryResult.Searching -> oldState.translateTo {
                searchResultState { Searching }
            }
            is SearchCountryResult.Error -> oldState.translateTo {
                searchResultState { Error(result.throwable.errorMessage) }
            }
            is SearchCountryResult.Success -> oldState.translateTo {
                val countries: List<CountryModel> =
                    countryModelMapper.mapToModelList(result.countries)
                searchResultState { ResultLoaded(countries) }
            }
        }
    }
}
