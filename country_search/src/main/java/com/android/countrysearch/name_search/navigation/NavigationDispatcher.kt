package com.android.countrysearch.name_search.navigation

import com.android.countrysearch.name_search.model.CountryModel

// TODO refactor navigation entirely
// TODO refactor modularisation entirely

interface NavigationDispatcher {
    fun openCountryDetail(model: CountryModel)
    fun goBack()
}
