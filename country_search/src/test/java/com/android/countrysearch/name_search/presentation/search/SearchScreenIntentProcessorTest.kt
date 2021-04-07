//package com.android.countrysearch.name_search.presentation.search
//
//import com.android.countrysearch.lib_country_search.domain.usecase.search.SearchCountry
//import com.android.countrysearch.lib_country_search.domain.usecase.countrylist.GetCountryList
//import com.android.countrysearch.name_search.TestPostExecutionThread
//import com.android.countrysearch.name_search.data.DummyData
//import com.android.countrysearch.name_search.fakes.FakeCountryListRepository
//import com.android.countrysearch.name_search.fakes.FakeSearchRepository
//import com.android.countrysearch.name_search.mapper.CountryModelMapper
//import com.android.countrysearch.name_search.presentation.search.SearchScreenResult.SearchCountryResult
//import com.android.countrysearch.presentation.mvi.base.ViewIntent
//import com.android.countrysearch.testutils.ERROR_MSG
//import com.android.countrysearch.testutils.FlowRecorder
//import com.android.countrysearch.testutils.ResponseType
//import com.android.countrysearch.testutils.containsElements
//import com.android.countrysearch.testutils.recordWith
//import com.google.common.truth.Truth.assertThat
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.test.TestCoroutineScope
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Test
//import java.net.SocketTimeoutException
//
//class SearchScreenIntentProcessorTest {
//
//    private val fakeCountriesRepository = FakeSearchRepository()
//
//    private val fakeSearchHistoryRepository = FakeCountryListRepository()
//
//    private val countryModelMapper = CountryModelMapper()
//
//    private val testPostExecutionThread = TestPostExecutionThread()
//
//    private val searchViewIntentProcessor =
//        SearchScreenIntentProcessor(
//            SearchCountry(fakeCountriesRepository, testPostExecutionThread),
//            SaveCountryDetail(fakeSearchHistoryRepository, testPostExecutionThread),
//            GetCountryList(fakeSearchHistoryRepository, testPostExecutionThread),
//            ClearSearchHistory(fakeSearchHistoryRepository, testPostExecutionThread),
//            countryModelMapper
//        )
//
//    private val resultRecorder: FlowRecorder<SearchScreenResult> = FlowRecorder(TestCoroutineScope())
//
//    @Test
//    fun `check that LoadSearchHistoryIntent returns SuccessResult`() = runBlockingTest {
//        val list: List<Countries> = DummyData.countryList
//        fakeSearchHistoryRepository.saveSearch(list.first())
//        recordSearchHistoryResult(LoadSearchHistory)
//        assertThat(resultRecorder.takeAll()).containsElements(SearchScreenResult.LoadedCountries(list))
//    }
//
//    @Test
//    fun `check that LoadSearchHistoryIntent returns EmptyResult`() = runBlockingTest {
//        recordSearchHistoryResult(LoadSearchHistory)
//        assertThat(resultRecorder.takeAll())
//            .containsElements(SearchScreenResult.LoadedCountries(emptyList()))
//    }
//
//    @Test
//    fun `check that ClearSearchHistoryIntent returns EmptyResult`() = runBlockingTest {
//        val list: List<Countries> = DummyData.countryList
//        fakeSearchHistoryRepository.saveSearch(list.first())
//        recordSearchHistoryResult(ClearSearchHistoryIntent)
//        assertThat(resultRecorder.takeAll())
//            .containsElements(SearchScreenResult.LoadedCountries(emptyList()))
//    }
//
//    @Test
//    fun `check that UpdateHistoryIntent returns History in order`() = runBlockingTest {
//        val first: Countries = DummyData.country
//        val second: Countries = DummyData.country.copy(name = "amig")
//        val third: Countries = DummyData.country.copy(birthYear = "1997")
//        fakeSearchHistoryRepository.saveSearch(first)
//        fakeSearchHistoryRepository.saveSearch(second)
//        fakeSearchHistoryRepository.saveSearch(third)
//        recordSearchHistoryResult(UpdateHistoryIntent(countryModelMapper.mapToModel(second)))
//        assertThat(resultRecorder.takeAll())
//            .containsElements(SearchScreenResult.LoadedCountries(listOf(second)))
//    }
//
//    @Test
//    fun `check that SearchCountriesIntent returns SuccessResult`() {
//        recordSearchResult(SearchIntent(DummyData.query), ResponseType.DATA)
//        assertThat(resultRecorder.takeAll())
//            .containsElements(
//                SearchCountryResult.Searching,
//                SearchCountryResult.Success(DummyData.countryList)
//            )
//    }
//
//    @Test
//    fun `SearchCountriesIntent returns emptySearchHistoryResult when query is empty and no search is saved`() {
//        searchViewIntentProcessor.intentToResult(SearchIntent(""))
//            .recordWith(resultRecorder)
//        assertThat(resultRecorder.takeAll())
//            .containsElements(SearchScreenResult.LoadedCountries(emptyList()))
//    }
//
//    @Test
//    fun `SearchCountriesIntent returns loadedSearchHistoryResult when query is empty and search is saved`() =
//        runBlockingTest {
//            val list: List<Countries> = DummyData.countryList
//            fakeSearchHistoryRepository.saveSearch(list.first())
//            searchViewIntentProcessor.intentToResult(SearchIntent(""))
//                .recordWith(resultRecorder)
//            assertThat(resultRecorder.takeAll()).containsElements(
//                SearchScreenResult.LoadedCountries(list)
//            )
//        }
//
//    @Test
//    fun `check that SearchCountriesIntent returns ErrorResult`() {
//        recordSearchResult(SearchIntent(DummyData.query), ResponseType.ERROR)
//        val results: List<SearchScreenResult> = resultRecorder.takeAll()
//        assertThat(results.map { it.javaClass })
//            .containsElements(
//                SearchCountryResult.Searching::class.java,
//                SearchCountryResult.Error::class.java
//            )
//        val errorResult: SearchCountryResult.Error = results.last() as SearchCountryResult.Error
//        assertThat(errorResult.throwable).isInstanceOf(SocketTimeoutException::class.java)
//        assertThat(errorResult.throwable.message).isEqualTo(ERROR_MSG)
//    }
//
//    @Test
//    fun `check that SaveSearchIntent saves current country`() = runBlockingTest {
//        val list: List<Countries> = DummyData.countryList
//        searchViewIntentProcessor.intentToResult(
//            SaveSearchIntent(countryModelMapper.mapToModel(list.first()))
//        ).collect()
//        recordSearchHistoryResult(LoadSearchHistory)
//        assertThat(resultRecorder.takeAll()).containsElements(SearchScreenResult.LoadedCountries(list))
//    }
//
//    private fun recordSearchResult(intent: ViewIntent, type: ResponseType) {
//        fakeCountriesRepository.responseType = type
//        searchViewIntentProcessor.intentToResult(intent).recordWith(resultRecorder)
//    }
//
//    private fun recordSearchHistoryResult(intent: ViewIntent) {
//        searchViewIntentProcessor.intentToResult(intent).recordWith(resultRecorder)
//    }
//}
