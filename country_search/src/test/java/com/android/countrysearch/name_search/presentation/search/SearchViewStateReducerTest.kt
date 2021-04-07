package com.android.countrysearch.name_search.presentation.search

import com.android.countrysearch.name_search.data.DummyData
import com.android.countrysearch.name_search.mapper.CountryModelMapper
import com.android.countrysearch.name_search.presentation.search.SearchScreenResult.SearchCountryResult
import com.android.countrysearch.testutils.ERROR_MSG
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class SearchScreenStateReducerTest {
//
    private val mapper = CountryModelMapper()
    private val reducer = SearchScreenStateReducer(mapper)

    @Test
    fun `check that emptySearchHistoryState is emitted when SearchHistoryResult is Empty`() {
        runBlockingTest {
            val initialState: SearchScreenState = SearchScreenState.Initial
            val viewState: SearchScreenState = reducer.reduce(
                initialState,
                SearchScreenResult.LoadedCountries(emptyList())
            )
            assertThat(viewState).isEqualTo(
                initialState.translateTo {
                    countryListState { DataLoaded(emptyList()) }
                }
            )
        }
    }
//
//    @Test
//    fun `check that SearchHistoryLoadedState is emitted when SearchHistoryResult is Success`() {
//        runBlockingTest {
//            val initialState: SearchScreenState = SearchScreenState.Initial
//            val list: List<Countries> = DummyData.countryList
//            val viewState: SearchScreenState =
//                reducer.reduce(initialState, SearchScreenResult.LoadedCountries(list))
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    countryListState { DataLoaded(mapper.mapToModelList(list)) }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that SearchingState is emitted when SearchCountriesResult is Searching`() {
//        runBlockingTest {
//            val initialState: SearchScreenState = SearchScreenState.Initial
//            val viewState: SearchScreenState =
//                reducer.reduce(initialState, SearchCountryResult.Searching)
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    searchResultState { Searching }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that SearchResultLoadedState is emitted when SearchCountriesResult is Success`() {
//        runBlockingTest {
//            val initialState: SearchScreenState = SearchScreenState.Initial
//            val list: List<Countries> = DummyData.countryList
//            val viewState: SearchScreenState =
//                reducer.reduce(initialState, SearchCountryResult.Success(list))
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    searchResultState { ResultLoaded(mapper.mapToModelList(list)) }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that SearchResultErrorState is emitted when SearchCountriesResult is Error`() {
//        runBlockingTest {
//            val initialState: SearchScreenState = SearchScreenState.Initial
//            val viewState: SearchScreenState =
//                reducer.reduce(initialState, SearchCountryResult.Error(Throwable(ERROR_MSG)))
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    searchResultState { Error(ERROR_MSG) }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that fall back error message is returned when SearchCountriesResult is Error`() {
//        runBlockingTest {
//            val initialState: SearchScreenState = SearchScreenState.Initial
//            val viewState: SearchScreenState =
//                reducer.reduce(initialState, SearchCountryResult.Error(Throwable()))
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    searchResultState { Error("An error occurred") }
//                }
//            )
//        }
//    }
}
