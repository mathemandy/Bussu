package com.android.countrysearch.name_search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CurrencyModel(
    val code: String,
    val name: String,
    val symbol: String,
) : Parcelable
