package com.android.countrysearch.name_search.views.search

import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleCoroutineScope
import com.android.countrysearch.core.ext.init
import com.android.countrysearch.core.ext.show
import com.android.countrysearch.name_search.databinding.LayoutCountryListBinding
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.ui.search.adapter.CountryListAdapter
import com.android.countrysearch.presentation.mvi.base.ViewIntent
import com.android.countrysearch.presentation.mvi.base.ViewState
import com.android.countrysearch.presentation_android.UIComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import ru.beryukhov.reactivenetwork.ReactiveNetwork


object  RetryFetchCountryIntent : ViewIntent


class CountryListView(
    private val binding: LayoutCountryListBinding,
    navigationAction: (CountryModel) -> Unit,
    private val scope: LifecycleCoroutineScope
) : UIComponent<CountryListViewState>() {

    private val countryListAdapter: CountryListAdapter by init {
        CountryListAdapter { model ->
            navigationAction(model)
        }
    }

    init {
        binding.countryListRv.adapter = countryListAdapter
        scope.launchWhenStarted {
            ReactiveNetwork().observeInternetConnectivity()
                .flowOn(Dispatchers.IO)
                .collect {
                sendIntent(RetryFetchCountryIntent)
            }
        }

    }

    override fun render(state: CountryListViewState) {
        countryListAdapter.submitList(state.countries)
        binding.run {
            recentSearchGroup.isVisible = state.showCountryGroup
            countryListRv.show = state.showCountry
            countryListPrompt.isVisible = state.showCountryPrompt
            countryLoader.isVisible  = state.showLoading
        }
    }
}

data class CountryListViewState private constructor(
    val countries: List<CountryModel> = emptyList(),
    val showCountry: Boolean = false,
    val showCountryGroup: Boolean = false,
    val showCountryPrompt: Boolean = false,
    val showLoading: Boolean = false
) : ViewState {

    inline fun state(transform: Factory.() -> CountryListViewState): CountryListViewState =
        transform(Factory(this))

    companion object Factory {

        private lateinit var state: CountryListViewState

        operator fun invoke(viewState: CountryListViewState): Factory {
            state = viewState
            return this
        }

        val Initial: CountryListViewState
            get() = CountryListViewState()

        val Hide: CountryListViewState
            get() = CountryListViewState()

        fun DataLoaded(countries: List<CountryModel>): CountryListViewState =
            state.copy(
                countries = countries,
                showCountry = countries.isNotEmpty(),
                showCountryGroup = countries.isNotEmpty(),
                showCountryPrompt = countries.isEmpty(),
                showLoading = false
            )
        fun  Loading() : CountryListViewState =
            state.copy(
                showCountry = state.countries.isNotEmpty(),
                showCountryGroup = state.countries.isNotEmpty(),
                showCountryPrompt = false,
                showLoading = state.countries.isEmpty()
            )

        fun Empty(reason  : String) : CountryListViewState =
            state.copy(
                showCountry = state.countries.isNotEmpty(),
                showCountryGroup = false,
                showCountryPrompt = state.countries.isEmpty(),
                showLoading = false
            )

    }
}
