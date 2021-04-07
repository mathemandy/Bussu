package com.android.countrysearch.name_search.presentation.search

import com.android.countrysearch.presentation.mvi.base.ViewIntent

sealed interface SearchScreenIntent : ViewIntent
data class RetrySearchIntent(val query: String) : SearchScreenIntent
data class SearchIntent(val query: String) : SearchScreenIntent
object LoadSearchHistory : SearchScreenIntent
