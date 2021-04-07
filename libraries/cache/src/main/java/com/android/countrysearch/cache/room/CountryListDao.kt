package com.android.countrysearch.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.countrysearch.cache.entity.CountryCacheModel
import com.android.countrysearch.cache.entity.CountryDetailCacheModel

@Dao
interface CountryListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryCacheModel: CountryCacheModel)

    @Query("SELECT * FROM COUNTRY_LIST")
    suspend fun countryList(): List<CountryCacheModel>

    @Query("SELECT * FROM COUNTRY_LIST WHERE name like :name")
    suspend fun fetchCountry(name: String): List<CountryCacheModel>

    @Query("DELETE FROM COUNTRY_LIST")
    suspend fun clearHistory()
}
