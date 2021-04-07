package com.android.countrysearch.name_search.presentation.search

import com.android.countrysearch.name_search.views.search.CountryListViewState
import com.android.countrysearch.name_search.views.search.SearchResultViewState
import com.android.countrysearch.presentation.mvi.base.ScreenState

data class SearchScreenState private constructor(
    val countryListState: CountryListViewState = CountryListViewState.Initial,
    val searchResultState: SearchResultViewState = SearchResultViewState.Initial
) : ScreenState {

    inline fun translateTo(transform: Factory.() -> SearchScreenState): SearchScreenState =
        transform(Factory(this))

    companion object Factory {

        private lateinit var state: SearchScreenState

        operator fun invoke(viewState: SearchScreenState): Factory {
            state = viewState
            return this
        }

        val Initial: SearchScreenState
            get() = SearchScreenState()

        fun countryListState(
            transform: CountryListViewState.Factory.() -> CountryListViewState
        ): SearchScreenState {
            return state.copy(
                searchResultState = state.searchResultState.state { Hide },
                countryListState = state.countryListState.state(transform)
            )
        }

        fun searchResultState(
            transform: SearchResultViewState.Factory.() -> SearchResultViewState
        ): SearchScreenState {
            return state.copy(
                searchResultState = state.searchResultState.state(transform),
                countryListState = state.countryListState.state { Hide }
            )
        }
    }
}
