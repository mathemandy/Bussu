package com.android.countrysearch.name_search.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.model.CountryModel
import javax.inject.Inject
import javax.inject.Provider

class NavigationDispatcherImpl @Inject constructor(
    private val navController: Provider<NavController>
) : NavigationDispatcher {

    override fun openCountryDetail(model: CountryModel) {
        navController.get().navigate(
            R.id.countryDetailFragment,
            bundleOf(COUNTRY_ARG to model)
        )
    }

    override fun goBack() {
        navController.get().navigateUp()
    }

    companion object {
        const val COUNTRY_ARG: String = "country"
    }
}
