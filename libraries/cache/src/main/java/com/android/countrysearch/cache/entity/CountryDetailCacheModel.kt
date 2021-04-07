package com.android.countrysearch.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRY_DETAIL")
data class CountryDetailCacheModel(
    val name: String,
    val capitalName: String,
    val currencies: List<CurrencyCacheModel>,
    @PrimaryKey
    val flag: String,
)
