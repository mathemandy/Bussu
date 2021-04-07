package com.android.countrysearch.lib_country_search.cache.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.countrysearch.cache.room.CountriesDatabase
import com.android.countrysearch.lib_country_search.cache.DummyData
import com.android.countrysearch.lib_country_search.cache.mapper.CountryCacheModelMapper
import com.android.countrysearch.lib_country_search.data.contract.cache.CountryListCache
import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryListCacheImplTest {

    private lateinit var countryListCache: CountryListCache
    private lateinit var countriesDatabase: CountriesDatabase

    @Before
    fun setup() {
        countriesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CountriesDatabase::class.java
        ).allowMainThreadQueries().build()

        countryListCache =
            CountryListCacheImpl(
                countriesDatabase.countryListDao,
                countriesDatabase.countryDetailDao,
                CountryCacheModelMapper()
            )
    }

    @Test
    fun `check that saveCountry inserts data into database`() = runBlocking {
        val country: CountryEntity = DummyData.entity
        countryListCache.saveCountry(country)

        val result: CountryEntity = countryListCache.getSavedCountries().first()
        assertThat(country.name).isEqualTo(result.name)
        assertThat(country.flag).isEqualTo(result.flag)

    }


    @Test
    fun `check that saveCountry replaces already saved item`() = runBlocking {
        val country: CountryEntity = DummyData.entity
        val name = "Albania"
        val country1: CountryEntity = DummyData.entity.copy(name = name)

        countryListCache.saveCountry(country)
        countryListCache.saveCountry(country1)

        val result: CountryEntity =
            countryListCache.getSavedCountries().first()
        assertThat(result).isNotEqualTo(country)
        assertThat(result.name).isEqualTo(name)
    }


    @Test
    fun `check that clearCountryList clears all saved countries`() = runBlocking {
        val country: CountryEntity = DummyData.entity
        val country3: CountryEntity = DummyData.entity.copy(name = "Algeria")

        countryListCache.saveCountry(country)
        countryListCache.saveCountry(country3)

        countryListCache.clearCountries()

        val result: List<CountryEntity> = countryListCache.getSavedCountries()
        assertThat(result).isEmpty()
    }

    @After
    fun tearDown() {
        countriesDatabase.close()
    }
}
