package com.android.countrysearch.lib_country_search.cache.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.countrysearch.cache.room.CountriesDatabase
import com.android.countrysearch.lib_country_search.cache.DummyData
import com.android.countrysearch.lib_country_search.cache.mapper.CountryDetailCacheMapper
import com.android.countrysearch.lib_country_search.cache.mapper.CurrencyCacheMapper
import com.android.countrysearch.lib_country_search.data.contract.cache.CountryDetailCache
import com.android.countrysearch.lib_country_search.data.model.CountryDetailEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryDetailCacheImplTest {

    private lateinit var cache: CountryDetailCache
    private lateinit var countriesDatabase: CountriesDatabase

    @Before
    fun setup() {
        countriesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CountriesDatabase::class.java
        ).allowMainThreadQueries().build()

        cache =
            CountryDetailCacheImpl(
                countriesDatabase.countryDetailDao,
                CountryDetailCacheMapper(CurrencyCacheMapper())
            )
    }

    @Test
    fun `check that saveCountry inserts data into database`() = runBlocking {
        val country: CountryDetailEntity = DummyData.countryDetailEntity

        cache.saveCountry(country)

        val result: CountryDetailEntity? = cache.fetchCountry(country.name)
        assertThat(result).isNotNull()
    }

    @Test
    fun `check that fetchCountry returns data`() = runBlocking {
        val country: CountryDetailEntity = DummyData.countryDetailEntity

        cache.saveCountry(country)

        val result: CountryDetailEntity? = cache.fetchCountry(country.name)
        assertThat(result).isNotNull()
        assertThat(result?.name).isEqualTo(country.name)
        assertThat(result?.capitalName).isEqualTo(country.capitalName)
        assertThat(result?.flag).isEqualTo(country.flag)
        assertThat(result?.currencies).isEqualTo(country.currencies)
    }

    @Test
    fun `check that fetchCountry returns null data if database is empty`() = runBlocking {
        val country: CountryDetailEntity = DummyData.countryDetailEntity
        val result: CountryDetailEntity? = cache.fetchCountry(country.name)
        assertThat(result).isNull()
    }

    @After
    fun tearDown() {
        countriesDatabase.close()
    }
}
