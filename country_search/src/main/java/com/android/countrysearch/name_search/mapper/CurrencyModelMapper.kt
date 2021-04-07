package com.android.countrysearch.name_search.mapper

import com.android.countrysearch.lib_country_search.domain.model.CurrencyDetail
import com.android.countrysearch.name_search.model.CurrencyModel
import com.android.countrysearch.presentation.mapper.ModelMapper
import javax.inject.Inject

class CurrencyModelMapper @Inject constructor() : ModelMapper<CurrencyModel, CurrencyDetail> {

    override fun mapToModel(domain: CurrencyDetail): CurrencyModel {
        return CurrencyModel(
           name = domain.name,
           symbol  =  domain.symbol,
            code = domain.code
        )
    }

    override fun mapToDomain(model: CurrencyModel): CurrencyDetail {
        return CurrencyDetail(
           name  =  model.name,
           symbol =  model.symbol,
           code  =  model.code
        )
    }
}