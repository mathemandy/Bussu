package com.android.countrysearch.lib_country_search.remote.mapper

import com.android.countrysearch.lib_country_search.data.model.CurrencyEntity
import com.android.countrysearch.lib_country_search.remote.model.response.Currency
import com.android.countrysearch.remote.base.RemoteModelMapper
import javax.inject.Inject

internal class CurrencyRemoteMapper @Inject constructor() :
    RemoteModelMapper<Currency, CurrencyEntity> {

    override fun mapFromModel(model: Currency): CurrencyEntity {
        return CurrencyEntity(safeString(model.code), safeString(model.name), safeString(model.symbol))
    }
}
