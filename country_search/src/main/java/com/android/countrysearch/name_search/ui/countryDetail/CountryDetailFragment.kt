package com.android.countrysearch.name_search.ui.countryDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.databinding.FragmentCountryDetailBinding
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.presentation.DetailComponentManager
import com.android.countrysearch.name_search.presentation.detail.CountryDetailViewStateMachine
import com.android.countrysearch.name_search.views.detail.DetailErrorView
import com.android.countrysearch.name_search.views.detail.CountryDetailView
import com.android.countrysearch.name_search.views.detail.CountryDetailViewState
import com.android.countrysearch.presentation.mvi.base.ViewIntent
import com.android.countrysearch.presentation_android.AssistedCreator
import com.android.countrysearch.presentation_android.assistedFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class CountriesDetailComponentManager @AssistedInject constructor(
    stateMachine: CountryDetailViewStateMachine.Factory,
    @Assisted country: CountryModel
) : DetailComponentManager(stateMachine.create(country)) {

    @AssistedFactory
    interface Creator : AssistedCreator<CountryModel, CountriesDetailComponentManager>
}

data class LoadCountriesDetailIntent(val country: CountryModel) : ViewIntent

@AndroidEntryPoint
class CountryDetailFragment : Fragment(R.layout.fragment_country_detail) {

    @Inject
    lateinit var creator: CountriesDetailComponentManager.Creator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: CountryDetailFragmentArgs by navArgs()

        val componentManager: CountriesDetailComponentManager by viewModels {
            assistedFactory(creator, args.country)
        }

        val binding = FragmentCountryDetailBinding.bind(view)

        componentManager.run {
            subscribe(
                DetailErrorView(binding.detailErrorState, args.country)
            ) { screenState -> screenState.errorViewState }
            subscribe(
                CountryDetailView(binding.countryDetailView)
            ) { screenState -> CountryDetailViewState(screenState.country, screenState.isLoading) }

            disposeAll(viewLifecycleOwner)
        }
    }
}
