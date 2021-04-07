package com.android.countrysearch.cache.room

import androidx.room.TypeConverter
import com.android.countrysearch.cache.entity.CurrencyCacheModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

internal class TypeConverter {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val adapter: JsonAdapter<List<CurrencyCacheModel>> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, CurrencyCacheModel::class.java)
        moshi.adapter(type)
    }

    @TypeConverter
    fun toList(value: String): List<CurrencyCacheModel>? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(value: List<CurrencyCacheModel>): String {
        return adapter.toJson(value)
    }
}
