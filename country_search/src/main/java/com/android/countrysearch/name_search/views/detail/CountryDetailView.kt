package com.android.countrysearch.name_search.views.detail

import androidx.core.view.isVisible
import coil.decode.SvgDecoder
import coil.load
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.databinding.CountryViewLayoutBinding
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.model.CurrencyModel
import com.android.countrysearch.presentation.mvi.base.ViewState
import com.android.countrysearch.presentation_android.UIComponent
import java.lang.StringBuilder

data class CountryDetailViewState(
    val country: CountryModel?,
    val isLoading: Boolean = false,
) : ViewState

class CountryDetailView(private val binding: CountryViewLayoutBinding) : UIComponent<CountryDetailViewState>() {


    override fun render(state: CountryDetailViewState) {
        val imageLoader = coil.ImageLoader.Builder(binding.root.context)
            .componentRegistry {
                add(SvgDecoder(binding.root.context))
            }.build()
        val (country: CountryModel?) = state
        if (country != null) {
            binding.run {
                profileTitle.text =root.context.getString(
                    R.string.country_name, country.name)
                capital.text = root.context.getString(
                    R.string.country_capital,country.capitalName)
                currency.text = root.context.getString(
                    R.string.country_currency_code,getCurrency(country.currencies))
                flag.load(country.flag, imageLoader)
            }
        }
        binding.countryLoader.isVisible  = state.isLoading

    }

    private fun getCurrency(currencies: List<CurrencyModel>?): String{
        val stringBuilder  = StringBuilder()
        currencies?.forEach {
            stringBuilder.append(it.symbol)
            stringBuilder.appendln()
        }

        return stringBuilder.toString()
    }
}
