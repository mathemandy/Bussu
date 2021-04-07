package com.android.countrysearch.name_search.views.detail

import androidx.core.view.isVisible
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.views.EmptyStateView
import com.android.countrysearch.presentation.mvi.base.ViewIntent
import com.android.countrysearch.presentation.mvi.base.ViewState
import com.android.countrysearch.presentation_android.UIComponent

data class RetryFetchCountriesDetailsIntent(val country: CountryModel) : ViewIntent

class DetailErrorView(
    private val view: EmptyStateView,
    country: CountryModel
) : UIComponent<DetailErrorViewState>() {

    init {
        view.onRetry { sendIntent(RetryFetchCountriesDetailsIntent(country)) }
    }


    override fun render(state: DetailErrorViewState) {
        view.run {
            isVisible = state.showError
            setCaption(state.errorMessage)
            setTitle(
                context.getString(
                    R.string.error_fetching_details,
                    state.countryName
                )
            )
        }
    }
}

data class DetailErrorViewState private constructor(
    val countryName: String = "",
    val errorMessage: String = "",
    val showError: Boolean = false
) : ViewState {

    inline fun state(transform: Factory.() -> DetailErrorViewState): DetailErrorViewState =
        transform(Factory(this))

    companion object Factory {

        private lateinit var state: DetailErrorViewState

        val Init: DetailErrorViewState
            get() = DetailErrorViewState()

        val Hide: DetailErrorViewState
            get() = DetailErrorViewState()

        operator fun invoke(viewState: DetailErrorViewState): Factory {
            state = viewState
            return this
        }

        fun DisplayError(countryName: String, errorMessage: String): DetailErrorViewState =
            state.copy(
                countryName = countryName,
                errorMessage = errorMessage,
                showError = true
            )
    }
}
