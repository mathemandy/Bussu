package com.android.countrysearch.name_search.presentation.detail

import com.android.countrysearch.lib_country_search.domain.usecase.detail.GetCountryDetail
import com.android.countrysearch.name_search.mapper.CountryDetailModelMapper
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.presentation.CountryDetailIntentProcessor
import com.android.countrysearch.name_search.ui.countryDetail.LoadCountriesDetailIntent
import com.android.countrysearch.name_search.views.detail.RetryFetchCountriesDetailsIntent
import com.android.countrysearch.presentation.mvi.base.InvalidViewIntentException
import com.android.countrysearch.presentation.mvi.base.ViewIntent
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CountryDetailViewIntentProcessor @Inject constructor(
    private val getCountryDetail: GetCountryDetail,
    private val countryModelMapper: CountryDetailModelMapper
) : CountryDetailIntentProcessor {

    override fun intentToResult(viewIntent: ViewIntent): Flow<CountryDetailViewResult> {
        return when (viewIntent) {
            is LoadCountriesDetailIntent -> {
                getCountryInfo(
                    viewIntent.country,
                    CountryDetailViewResult.CountryDetail(
                        countryModelMapper.mapToDomain(viewIntent.country)
                    )
                )
            }
            is RetryFetchCountriesDetailsIntent ->
                getCountryInfo(viewIntent.country, CountryDetailViewResult.Retrying)
            else -> throw InvalidViewIntentException(viewIntent)
        }
    }

    private fun getCountryInfo(
        model: CountryModel,
        start: CountryDetailViewResult
    ): Flow<CountryDetailViewResult> {
        return getCountryDetail(model.name)
            .map { countryDetail ->
                if (countryDetail.flag.isEmpty()){
                    CountryDetailViewResult.FetchCountryDetailError(model.name, IllegalArgumentException("Invalid data"))

                }else {
                    CountryDetailViewResult.CountryDetail(countryDetail)
                }
            }
            .onStart { emit(CountryDetailViewResult.Loading) }
            .catch { error ->
                emit(CountryDetailViewResult.FetchCountryDetailError(model.name, error))
            }
    }




}
