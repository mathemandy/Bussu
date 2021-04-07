package com.android.countrysearch.lib_country_search.domain.usecase.countrylist

import com.android.countrysearch.lib_country_search.domain.executor.PostExecutionThread
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.repository.CountryListRepository
import com.android.countrysearch.lib_country_search.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountryList @Inject constructor(
    private val countryListRepository: CountryListRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<Unit, List<Country>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: Unit?): Flow<List<Country>> {
        return countryListRepository.getCountryList()
    }
}
