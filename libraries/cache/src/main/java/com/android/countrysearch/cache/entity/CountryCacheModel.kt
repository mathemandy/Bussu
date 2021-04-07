package com.android.countrysearch.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COUNTRY_LIST")
data class CountryCacheModel(
    val name: String,
    @PrimaryKey
    val flag: String,
)
