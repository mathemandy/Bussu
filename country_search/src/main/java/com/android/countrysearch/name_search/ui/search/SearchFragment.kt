package com.android.countrysearch.name_search.ui.search

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.countrysearch.core.ext.lazyText
import com.android.countrysearch.core.ext.onBackPress
import com.android.countrysearch.core.ext.viewScope
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.databinding.FragmentSearchBinding
import com.android.countrysearch.name_search.navigation.NavigationDispatcher
import com.android.countrysearch.name_search.presentation.SearchComponentManager
import com.android.countrysearch.name_search.presentation.SearchStateMachine
import com.android.countrysearch.name_search.views.search.SearchBarView
import com.android.countrysearch.name_search.views.search.CountryListView
import com.android.countrysearch.name_search.views.search.SearchResultView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountriesSearchComponentManager @Inject constructor(
    searchStateMachine: SearchStateMachine
) : SearchComponentManager(searchStateMachine)

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var navigator: NavigationDispatcher

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val componentManager: CountriesSearchComponentManager by viewModels()

        val binding = FragmentSearchBinding.bind(view)

        handleBackPress(binding.searchBar)

        componentManager.run {
            subscribe(SearchBarView(binding.searchBar, viewScope))
            subscribe(
                CountryListView(
                    binding.countryList,
                    navigator::openCountryDetail,
                    lifecycleScope
                )
            ) { screenState -> screenState.countryListState }
            subscribe(
                SearchResultView(
                    binding.searchResult,
                    binding.searchBar.lazyText,
                    navigator::openCountryDetail
                )
            ) { screenState -> screenState.searchResultState }

            disposeAll(viewLifecycleOwner)
        }
    }

    private fun handleBackPress(editText: EditText) {
        onBackPress {
            if (editText.text.isNotEmpty()) {
                editText.text.clear()
            } else {
                requireActivity().finish()
            }
        }
    }
}
