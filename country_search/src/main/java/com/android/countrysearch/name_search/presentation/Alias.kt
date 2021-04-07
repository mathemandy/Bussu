package com.android.countrysearch.name_search.presentation

import com.android.countrysearch.name_search.presentation.detail.CountryDetailViewResult
import com.android.countrysearch.name_search.presentation.detail.CountryDetailViewState
import com.android.countrysearch.name_search.presentation.search.SearchScreenResult
import com.android.countrysearch.name_search.presentation.search.SearchScreenState
import com.android.countrysearch.presentation.mvi.base.IntentProcessor
import com.android.countrysearch.presentation.mvi.base.ViewStateReducer
import com.android.countrysearch.presentation.mvi.stateMachine.StateMachine
import com.android.countrysearch.presentation_android.ComponentManager

typealias SearchIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<SearchScreenResult>

typealias SearchStateReducer =
    @JvmSuppressWildcards ViewStateReducer<SearchScreenState, SearchScreenResult>

typealias SearchStateMachine =
    @JvmSuppressWildcards StateMachine<SearchScreenState, SearchScreenResult>

typealias SearchComponentManager =
    @JvmSuppressWildcards ComponentManager<SearchScreenState, SearchScreenResult>

typealias CountryDetailIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<CountryDetailViewResult>

typealias CountryDetailStateReducer =
    @JvmSuppressWildcards ViewStateReducer<CountryDetailViewState, CountryDetailViewResult>

typealias CountryDetailStateMachine =
    @JvmSuppressWildcards StateMachine<CountryDetailViewState, CountryDetailViewResult>

typealias DetailComponentManager =
    @JvmSuppressWildcards ComponentManager<CountryDetailViewState, CountryDetailViewResult>
