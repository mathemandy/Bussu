package com.android.countrysearch.name_search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDetailModel(
    val name: String,
    val capitalName: String?  = "",
    val currencies: List<CurrencyModel>? = emptyList(),
    val flag: String? = "",
) : Parcelable
