//package com.android.countrysearch.name_search.presentation.detail
//
//import com.android.countrysearch.lib_country_search.domain.usecase.detail.GetCountryDetail
//import com.android.countrysearch.name_search.TestPostExecutionThread
//import com.android.countrysearch.name_search.data.DummyData
//import com.android.countrysearch.name_search.fakes.FakeCountryDetailRepository
//import com.android.countrysearch.name_search.mapper.CountryModelMapper
//import com.android.countrysearch.name_search.model.CountryModel
//import com.android.countrysearch.name_search.ui.countryDetail.LoadCountriesDetailIntent
//import com.android.countrysearch.name_search.views.detail.RetryFetchCountriesDetailsIntent
//import com.android.countrysearch.testutils.ERROR_MSG
//import com.android.countrysearch.testutils.FlowRecorder
//import com.android.countrysearch.testutils.ResponseType
//import com.android.countrysearch.testutils.containsElements
//import com.android.countrysearch.testutils.recordWith
//import com.google.common.truth.Truth.assertThat
//import kotlinx.coroutines.test.TestCoroutineScope
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Test
//import java.net.SocketTimeoutException
//
//class CountryDetailViewIntentProcessorTest {
//
//    private val repository = FakeCountryDetailRepository()
//    private val countryModelMapper = CountryModelMapper()
//    private val testPostExecutionThread = TestPostExecutionThread()
//
//    private val processor =
//        CountryDetailViewIntentProcessor(
//            GetCountryDetail(repository, testPostExecutionThread),
//            countryModelMapper
//        )
//
//    private val resultRecorder: FlowRecorder<CountryDetailViewResult> =
//        FlowRecorder(TestCoroutineScope())
//
//    @Test
//    fun `check that LoadCountriesDetailIntent returns country details`() = runBlockingTest {
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            LoadCountriesDetailIntent(country)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll()).containsElements(
//            CountryDetailViewResult.CountryDetail(
//                countryModelMapper.mapToDomain(country)
//            ),
//            FilmDetailViewResult.Loading,
//            FilmDetailViewResult.Success(DummyData.films),
//            PlanetDetailViewResult.Loading,
//            PlanetDetailViewResult.Success(DummyData.planet),
//            SpecieDetailViewResult.Loading,
//            SpecieDetailViewResult.Success(DummyData.species)
//        )
//    }
//
//    @Test
//    fun `check that LoadCountriesDetailIntent returns errorResult if getCountriesDetail fails`() =
//        runBlockingTest {
//            repository.countryListResponseType = ResponseType.ERROR
//            val country: CountryModel = DummyData.countryModel
//            processor.intentToResult(
//                LoadCountriesDetailIntent(country)
//            ).recordWith(resultRecorder)
//
//            val results: List<CountryDetailViewResult> = resultRecorder.takeAll()
//            assertThat(results.map { it.javaClass })
//                .containsElements(
//                    CountryDetailViewResult.CountryDetail::class.java,
//                    CountryDetailViewResult.FetchCountryDetailError::class.java
//                )
//
//            val errorResult: CountryDetailViewResult.FetchCountryDetailError =
//                results.last() as CountryDetailViewResult.FetchCountryDetailError
//            assertThat(errorResult.error).isInstanceOf(SocketTimeoutException::class.java)
//            assertThat(errorResult.error.message).isEqualTo(ERROR_MSG)
//        }
//
//    @Test
//    fun `check that LoadCountriesDetailIntent returns errorResult when getFilms fails`() =
//        runBlockingTest {
//            repository.filmResponseType = ResponseType.ERROR
//
//            val country: CountryModel = DummyData.countryModel
//            processor.intentToResult(
//                LoadCountriesDetailIntent(country)
//            ).recordWith(resultRecorder)
//
//            val results: List<CountryDetailViewResult> = resultRecorder.takeAll()
//            assertThat(results.map { it.javaClass })
//                .containsElements(
//                    CountryDetailViewResult.CountryDetail::class.java,
//                    FilmDetailViewResult.Loading::class.java,
//                    FilmDetailViewResult.Error::class.java,
//                    PlanetDetailViewResult.Loading::class.java,
//                    PlanetDetailViewResult.Success::class.java,
//                    SpecieDetailViewResult.Loading::class.java,
//                    SpecieDetailViewResult.Success::class.java
//                )
//        }
//
//    @Test
//    fun `check that LoadCountriesDetailIntent returns errorResult when getPlanet fails`() =
//        runBlockingTest {
//            repository.planetResponseType = ResponseType.ERROR
//
//            val country: CountryModel = DummyData.countryModel
//            processor.intentToResult(
//                LoadCountriesDetailIntent(country)
//            ).recordWith(resultRecorder)
//
//            val results: List<CountryDetailViewResult> = resultRecorder.takeAll()
//            assertThat(results.map { it.javaClass })
//                .containsElements(
//                    CountryDetailViewResult.CountryDetail::class.java,
//                    FilmDetailViewResult.Loading::class.java,
//                    FilmDetailViewResult.Success::class.java,
//                    PlanetDetailViewResult.Loading::class.java,
//                    PlanetDetailViewResult.Error::class.java,
//                    SpecieDetailViewResult.Loading::class.java,
//                    SpecieDetailViewResult.Success::class.java
//                )
//        }
//
//    @Test
//    fun `check that LoadCountriesDetailIntent returns errorResult when getSpecies fails`() =
//        runBlockingTest {
//            repository.specieResponseType = ResponseType.ERROR
//
//            val country: CountryModel = DummyData.countryModel
//            processor.intentToResult(
//                LoadCountriesDetailIntent(country)
//            ).recordWith(resultRecorder)
//
//            val results: List<CountryDetailViewResult> = resultRecorder.takeAll()
//            assertThat(results.map { it.javaClass })
//                .containsElements(
//                    CountryDetailViewResult.CountryDetail::class.java,
//                    FilmDetailViewResult.Loading::class.java,
//                    FilmDetailViewResult.Success::class.java,
//                    PlanetDetailViewResult.Loading::class.java,
//                    PlanetDetailViewResult.Success::class.java,
//                    SpecieDetailViewResult.Loading::class.java,
//                    SpecieDetailViewResult.Error::class.java
//                )
//        }
//
//    @Test
//    fun `check that LoadCountriesDetailIntent returns errorResult when all calls fail`() =
//        runBlockingTest {
//            repository.specieResponseType = ResponseType.ERROR
//            repository.filmResponseType = ResponseType.ERROR
//            repository.planetResponseType = ResponseType.ERROR
//
//            val country: CountryModel = DummyData.countryModel
//            processor.intentToResult(
//                LoadCountriesDetailIntent(country)
//            ).recordWith(resultRecorder)
//
//            val results: List<CountryDetailViewResult> = resultRecorder.takeAll()
//            assertThat(results.map { it.javaClass })
//                .containsElements(
//                    CountryDetailViewResult.CountryDetail::class.java,
//                    FilmDetailViewResult.Loading::class.java,
//                    FilmDetailViewResult.Error::class.java,
//                    PlanetDetailViewResult.Loading::class.java,
//                    PlanetDetailViewResult.Error::class.java,
//                    SpecieDetailViewResult.Loading::class.java,
//                    SpecieDetailViewResult.Error::class.java
//                )
//        }
//
//    @Test
//    fun `check that RetryFetchPlanet returns data`() = runBlockingTest {
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchPlanetIntent(country.url)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll())
//            .containsElements(
//                PlanetDetailViewResult.Loading,
//                PlanetDetailViewResult.Success(DummyData.planet)
//            )
//    }
//
//    @Test
//    fun `check that RetryFetchPlanet returns error`() = runBlockingTest {
//        repository.planetResponseType = ResponseType.ERROR
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchPlanetIntent(country.url)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll().map { it.javaClass })
//            .containsElements(
//                PlanetDetailViewResult.Loading::class.java,
//                PlanetDetailViewResult.Error::class.java
//            )
//    }
//
//    @Test
//    fun `check that RetryFetchSpecie returns data`() = runBlockingTest {
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchSpecieIntent(country.url)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll())
//            .containsElements(
//                SpecieDetailViewResult.Loading,
//                SpecieDetailViewResult.Success(DummyData.species)
//            )
//    }
//
//    @Test
//    fun `check that RetryFetchSpecie returns error`() = runBlockingTest {
//        repository.specieResponseType = ResponseType.ERROR
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchSpecieIntent(country.url)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll().map { it.javaClass })
//            .containsElements(
//                SpecieDetailViewResult.Loading::class.java,
//                SpecieDetailViewResult.Error::class.java
//            )
//    }
//
//    @Test
//    fun `check that RetryFetchFilm returns data`() = runBlockingTest {
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchFilmIntent(country.url)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll())
//            .containsElements(
//                FilmDetailViewResult.Loading,
//                FilmDetailViewResult.Success(DummyData.films)
//            )
//    }
//
//    @Test
//    fun `check that RetryFetchFilm returns error`() = runBlockingTest {
//        repository.filmResponseType = ResponseType.ERROR
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchFilmIntent(country.url)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll().map { it.javaClass })
//            .containsElements(
//                FilmDetailViewResult.Loading::class.java,
//                FilmDetailViewResult.Error::class.java
//            )
//    }
//
//    @Test
//    fun `check that RetryFetchCountriesDetails returns data`() = runBlockingTest {
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchCountriesDetailsIntent(country)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll()).containsElements(
//            CountryDetailViewResult.Retrying,
//            FilmDetailViewResult.Loading,
//            FilmDetailViewResult.Success(DummyData.films),
//            PlanetDetailViewResult.Loading,
//            PlanetDetailViewResult.Success(DummyData.planet),
//            SpecieDetailViewResult.Loading,
//            SpecieDetailViewResult.Success(DummyData.species)
//        )
//    }
//
//    @Test
//    fun `check that RetryFetchCountriesDetails returns error`() = runBlockingTest {
//        repository.countryListResponseType = ResponseType.ERROR
//        val country: CountryModel = DummyData.countryModel
//
//        processor.intentToResult(
//            RetryFetchCountriesDetailsIntent(country)
//        ).recordWith(resultRecorder)
//
//        assertThat(resultRecorder.takeAll().map { it.javaClass })
//            .containsElements(
//                CountryDetailViewResult.Retrying::class.java,
//                CountryDetailViewResult.FetchCountryDetailError::class.java
//            )
//    }
//}
