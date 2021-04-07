package com.android.countrysearch.lib_country_search.domain.usecase.detail

import com.android.countrysearch.lib_country_search.domain.exception.requireParams
import com.android.countrysearch.lib_country_search.domain.executor.PostExecutionThread
import com.android.countrysearch.lib_country_search.domain.model.CountryDetail
import com.android.countrysearch.lib_country_search.domain.repository.CountryDetailRepository
import com.android.countrysearch.lib_country_search.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountryDetail @Inject constructor(
    private val countryDetailRepository: CountryDetailRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<String, CountryDetail>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: String?): Flow<CountryDetail> {
        requireParams(params)
        return countryDetailRepository.getCountryDetail(params)
    }
}
