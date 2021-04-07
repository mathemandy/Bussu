package com.android.countrysearch.lib_country_search.remote.mapper

import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import com.android.countrysearch.remote.base.RemoteModelMapper
import javax.inject.Inject

internal class CountryRemoteModelMapper @Inject constructor() :
    RemoteModelMapper<CountryRemoteModel?, CountryEntity> {

    override fun mapFromModel(model: CountryRemoteModel?): CountryEntity {
        return CountryEntity(
            safeString(model?.name),
            safeString(model?.flag)
        )
    }
}
