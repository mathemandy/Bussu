package com.android.countrysearch.name_search.presentation.detail

import com.android.countrysearch.core.ext.errorMessage
import com.android.countrysearch.name_search.mapper.CountryDetailModelMapper
import com.android.countrysearch.name_search.presentation.CountryDetailStateReducer
import com.android.countrysearch.name_search.presentation.detail.CountryDetailViewResult.CountryDetail
import com.android.countrysearch.name_search.presentation.detail.CountryDetailViewResult.FetchCountryDetailError
import javax.inject.Inject

class CountryDetailViewStateReducer @Inject constructor(
    private val countryModelMapper: CountryDetailModelMapper
) : CountryDetailStateReducer {

    override fun reduce(
        oldState: CountryDetailViewState,
        result: CountryDetailViewResult
    ): CountryDetailViewState {
        return when (result) {
            is CountryDetail -> oldState.translateTo {
                profileState(countryModelMapper.mapToModel(result.countryDetail))
            }
            is FetchCountryDetailError -> oldState.translateTo {
                errorState(result.countryName, result.error.errorMessage)
            }
            CountryDetailViewResult.Retrying -> oldState.translateTo { retryState }

            CountryDetailViewResult.Loading -> oldState.translateTo { loadingState()}
        }
    }


}
