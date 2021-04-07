package com.android.countrysearch.lib_country_search.remote.mapper

import com.android.countrysearch.lib_country_search.domain.model.CurrencyDetail
import com.android.countrysearch.lib_country_search.remote.model.response.Currency
import com.android.countrysearch.remote.base.RemoteModelMapper
import javax.inject.Inject

internal class CurrencyDetailRemoteMapper @Inject constructor() :
    RemoteModelMapper<Currency, CurrencyDetail> {

    override fun mapFromModel(model: Currency): CurrencyDetail {
        return CurrencyDetail(safeString(model.code), safeString(model.name), safeString(model.symbol))
    }
}