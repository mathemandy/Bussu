package com.android.countrysearch.name_search.presentation.search

import com.android.countrysearch.name_search.presentation.SearchIntentProcessor
import com.android.countrysearch.name_search.presentation.SearchStateMachine
import com.android.countrysearch.name_search.presentation.SearchStateReducer
import com.android.countrysearch.presentation.mvi.stateMachine.Latest
import javax.inject.Inject

class SearchScreenStateMachine @Inject constructor(
    intentProcessor: SearchIntentProcessor,
    reducer: SearchStateReducer
) : SearchStateMachine(
    intentProcessor,
    reducer,
    SearchScreenState.Initial,
    LoadSearchHistory,
    Latest
)
