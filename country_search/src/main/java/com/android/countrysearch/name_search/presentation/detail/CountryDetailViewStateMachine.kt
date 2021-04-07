package com.android.countrysearch.name_search.presentation.detail

import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.presentation.CountryDetailIntentProcessor
import com.android.countrysearch.name_search.presentation.CountryDetailStateMachine
import com.android.countrysearch.name_search.presentation.CountryDetailStateReducer
import com.android.countrysearch.name_search.ui.countryDetail.LoadCountriesDetailIntent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class CountryDetailViewStateMachine @AssistedInject constructor(
    intentProcessor: CountryDetailIntentProcessor,
    reducer: CountryDetailStateReducer,
    @Assisted country: CountryModel
) : CountryDetailStateMachine(
    intentProcessor,
    reducer,
    CountryDetailViewState.init,
    LoadCountriesDetailIntent(country)
) {

    @AssistedFactory
    interface Factory {
        fun create(country: CountryModel): CountryDetailViewStateMachine
    }
}
