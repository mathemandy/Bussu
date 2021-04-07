package com.android.countrysearch.name_search.di

import com.android.countrysearch.name_search.presentation.SearchIntentProcessor
import com.android.countrysearch.name_search.presentation.SearchStateMachine
import com.android.countrysearch.name_search.presentation.SearchStateReducer
import com.android.countrysearch.name_search.presentation.search.SearchScreenIntentProcessor
import com.android.countrysearch.name_search.presentation.search.SearchScreenStateMachine
import com.android.countrysearch.name_search.presentation.search.SearchScreenStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
interface SearchCountryModule {

    @get:Binds
    val SearchScreenIntentProcessor.intentProcessor: SearchIntentProcessor

    @get:Binds
    val SearchScreenStateReducer.reducer: SearchStateReducer

    @get:Binds
    val SearchScreenStateMachine.stateMachine: SearchStateMachine
}
