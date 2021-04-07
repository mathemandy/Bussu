package com.android.countrysearch.lib_country_search.domain.usecase.search

import com.android.countrysearch.lib_country_search.domain.exception.requireParams
import com.android.countrysearch.lib_country_search.domain.executor.PostExecutionThread
import com.android.countrysearch.lib_country_search.domain.model.Country
import com.android.countrysearch.lib_country_search.domain.repository.SearchRepository
import com.android.countrysearch.lib_country_search.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCountry @Inject constructor(
    private val repository: SearchRepository,
    private val postExecutionThread: PostExecutionThread
) : FlowUseCase<String, List<Country>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecutionThread.io

    override fun execute(params: String?): Flow<List<Country>> {
        requireParams(params)
        return repository.searchCountries(params)
    }
}
