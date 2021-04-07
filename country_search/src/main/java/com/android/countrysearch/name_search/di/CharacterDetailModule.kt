package com.android.countrysearch.name_search.di

import com.android.countrysearch.name_search.presentation.CountryDetailIntentProcessor
import com.android.countrysearch.name_search.presentation.CountryDetailStateReducer
import com.android.countrysearch.name_search.presentation.detail.CountryDetailViewIntentProcessor
import com.android.countrysearch.name_search.presentation.detail.CountryDetailViewStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
interface CountryDetailModule {

    @get:Binds
    val CountryDetailViewIntentProcessor.intentProcessor: CountryDetailIntentProcessor

    @get:Binds
    val CountryDetailViewStateReducer.reducer: CountryDetailStateReducer
}
