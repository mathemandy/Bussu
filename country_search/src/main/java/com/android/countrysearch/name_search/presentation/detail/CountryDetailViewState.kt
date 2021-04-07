package com.android.countrysearch.name_search.presentation.detail

import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.views.detail.DetailErrorViewState
import com.android.countrysearch.presentation.mvi.base.ScreenState

data class CountryDetailViewState private constructor(
    val country: CountryModel? = null,
    val errorViewState: DetailErrorViewState = DetailErrorViewState.Init,
    val isLoading : Boolean  = false
) : ScreenState {

    inline fun translateTo(transform: Factory.() -> CountryDetailViewState): CountryDetailViewState =
        transform(Factory(this))

    companion object Factory {

        private lateinit var state: CountryDetailViewState

        operator fun invoke(viewState: CountryDetailViewState): Factory {
            state = viewState
            return this
        }

        val init: CountryDetailViewState
            get() = CountryDetailViewState()

        fun profileState(country: CountryModel?): CountryDetailViewState =
            state.copy(country = country, isLoading = false)


        fun errorState(countryName: String, error: String): CountryDetailViewState =
            state.copy(
                errorViewState = state.errorViewState.state { DisplayError(countryName, error) },)

        val retryState: CountryDetailViewState
            get() = state.copy(
                errorViewState = state.errorViewState.state { Hide },
            )

        fun loadingState(): CountryDetailViewState = state.copy(
                isLoading =  true)
    }
}
