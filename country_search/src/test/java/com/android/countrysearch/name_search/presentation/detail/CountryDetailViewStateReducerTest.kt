//package com.android.countrysearch.name_search.presentation.detail
//
//import com.android.countrysearch.core.ext.errorMessage
//import com.android.countrysearch.name_search.data.DummyData
//import com.android.countrysearch.name_search.mapper.CountryModelMapper
//import com.google.common.truth.Truth.assertThat
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Test
//import java.net.SocketTimeoutException
//
//class CountryDetailViewStateReducerTest {
//
//    private val planetModelMapper: PlanetModelMapper = PlanetModelMapper()
//    private val specieModelMapper: SpecieModelMapper = SpecieModelMapper()
//    private val filmModelMapper: FilmModelMapper = FilmModelMapper()
//    private val countryModelMapper: CountryModelMapper = CountryModelMapper()
//
//    private val reducer = CountryDetailViewStateReducer(
//        planetModelMapper,
//        specieModelMapper,
//        filmModelMapper,
//        countryModelMapper
//    )
//
//    @Test
//    fun `check that CountriesDetailViewState is emitted when result is CountriesDetail`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//            val country: Countries = DummyData.country
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, CountryDetailViewResult.CountryDetail(country))
//
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo { profileState(countryModelMapper.mapToModel(country)) }
//            )
//        }
//    }
//
//    @Test
//    fun `check that RetryingViewState is emitted when result is Retrying`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, CountryDetailViewResult.Retrying)
//
//            assertThat(viewState).isEqualTo(initialState.translateTo { retryState })
//        }
//    }
//
//    @Test
//    fun `check that FetchDetailErrorViewState is emitted when result is FetchCountriesDetailError`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val error: SocketTimeoutException = DummyData.exception
//            val countryName = DummyData.country.name
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(
//                    initialState,
//                    CountryDetailViewResult.FetchCountryDetailError(countryName, error)
//                )
//
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo { errorState(countryName, error.errorMessage) }
//            )
//        }
//    }
//
//    @Test
//    fun `check that PlanetDetailViewStateSuccess is emitted when result is PlanetDetailViewResultSuccess`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val planet: Planet = DummyData.planet
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, PlanetDetailViewResult.Success(planet))
//
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    planetState { Success(planetModelMapper.mapToModel(planet)) }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that PlanetDetailViewStateLoading is emitted when result is PlanetDetailViewResultLoading`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, PlanetDetailViewResult.Loading)
//
//            assertThat(viewState).isEqualTo(initialState.translateTo { planetState { Loading } })
//        }
//    }
//
//    @Test
//    fun `check that PlanetDetailViewStateError is emitted when result is PlanetDetailViewResultError`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val error: SocketTimeoutException = DummyData.exception
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, PlanetDetailViewResult.Error(error))
//
//            assertThat(viewState).isEqualTo(initialState.translateTo { planetState { Error(error.errorMessage) } })
//        }
//    }
//
//    @Test
//    fun `check that FilmDetailViewStateSuccess is emitted when result is FilmDetailViewResultSuccess`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val films: List<Film> = DummyData.films
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, FilmDetailViewResult.Success(films))
//
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    filmState { success(filmModelMapper.mapToModelList(films)) }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that FilmDetailViewStateLoading is emitted when result is FilmDetailViewResultLoading`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, FilmDetailViewResult.Loading)
//
//            assertThat(viewState).isEqualTo(initialState.translateTo { filmState { loading } })
//        }
//    }
//
//    @Test
//    fun `check that FilmDetailViewStateLError is emitted when result is FilmDetailViewResultError`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val error: SocketTimeoutException = DummyData.exception
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, FilmDetailViewResult.Error(error))
//
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    filmState { error(error.errorMessage) }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that SpecieDetailViewStateSuccess is emitted when result is SpecieDetailViewResultSuccess`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val species: List<Specie> = DummyData.species
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, SpecieDetailViewResult.Success(species))
//
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    specieState { DataLoaded(specieModelMapper.mapToModelList(species)) }
//                }
//            )
//        }
//    }
//
//    @Test
//    fun `check that SpecieDetailViewStateLoading is emitted when result is SpecieDetailViewResultLoading`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, SpecieDetailViewResult.Loading)
//
//            assertThat(viewState).isEqualTo(initialState.translateTo { specieState { Loading } })
//        }
//    }
//
//    @Test
//    fun `check that SpecieDetailViewStateError is emitted when result is SpecieDetailViewResultError`() {
//        runBlockingTest {
//            val initialState: CountryDetailViewState = CountryDetailViewState.init
//
//            val error: SocketTimeoutException = DummyData.exception
//            val viewState: CountryDetailViewState =
//                reducer.reduce(initialState, SpecieDetailViewResult.Error(error))
//
//            assertThat(viewState).isEqualTo(
//                initialState.translateTo {
//                    specieState { Error(error.errorMessage) }
//                }
//            )
//        }
//    }
//}
