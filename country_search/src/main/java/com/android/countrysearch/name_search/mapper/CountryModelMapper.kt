package com.android.countrysearch.name_search.mapper

import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.presentation.mapper.ModelMapper
import javax.inject.Inject

class CountryModelMapper @Inject constructor() : ModelMapper<CountryModel, Country> {

    override fun mapToModel(domain: Country): CountryModel {
        return CountryModel(
            name =  domain.name,
            flag = domain.flag,
        )
    }

    override fun mapToDomain(model: CountryModel): Country {
        return Country(
            name = model.name,
            flag = safeString(model.flag),
        )
    }
}
