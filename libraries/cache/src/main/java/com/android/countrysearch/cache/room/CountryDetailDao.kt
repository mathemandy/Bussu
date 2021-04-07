package com.android.countrysearch.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.countrysearch.cache.entity.CountryDetailCacheModel

@Dao
interface CountryDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryDetailCacheModel: CountryDetailCacheModel)

    @Query("SELECT * FROM COUNTRY_DETAIL WHERE name = :name")
    suspend fun fetchCountry(name: String): CountryDetailCacheModel?

    @Query("DELETE FROM COUNTRY_DETAIL")
    suspend fun clearData()
}
