package com.android.countrysearch.lib_country_search.remote.mapper

import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import com.android.countrysearch.remote.base.RemoteModelMapper
import javax.inject.Inject

internal class CountryDetailRemoteMapper @Inject constructor(
    private val currencyDetailRemoteMapper: CurrencyDetailRemoteMapper
) :
    RemoteModelMapper<CountryRemoteModel?, CountryDetail> {

    override fun mapFromModel(model: CountryRemoteModel?): CountryDetail {
        return CountryDetail(
            safeString(model?.name),
            safeString(model?.capital),
            currencyDetailRemoteMapper.mapModelList(model?.currencies ?: emptyList()),
            safeString(model?.flag),
        )
    }
}
