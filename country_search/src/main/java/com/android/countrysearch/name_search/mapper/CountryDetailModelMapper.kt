package com.android.countrysearch.name_search.mapper

import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.presentation.mapper.ModelMapper
import javax.inject.Inject

class CountryDetailModelMapper @Inject constructor(
    private val currencyModelMapper:  CurrencyModelMapper
) : ModelMapper<CountryModel, CountryDetail> {

    override fun mapToModel(domain: CountryDetail): CountryModel {
        return CountryModel(
            name = domain.name,
            capitalName = domain.capitalName,
            currencies = currencyModelMapper.mapToModelList(domain.currencies),
            flag = domain.flag,
        )
    }

    override fun mapToDomain(model: CountryModel): CountryDetail {
        return CountryDetail(
            name = model.name,
            capitalName = safeString(model.capitalName),
            currencies = currencyModelMapper.mapToDomainList(model.currencies ?: emptyList()),
            flag = safeString(model.flag),
        )
    }
}
