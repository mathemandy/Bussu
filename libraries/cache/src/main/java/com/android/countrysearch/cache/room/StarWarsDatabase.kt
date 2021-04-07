package com.android.countrysearch.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.countrysearch.cache.BuildConfig

import com.android.countrysearch.cache.entity.CountryCacheModel
import com.android.countrysearch.cache.entity.CountryDetailCacheModel

@Database(
    entities = [CountryCacheModel::class, CountryDetailCacheModel::class],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class CountriesDatabase : RoomDatabase() {

    abstract val countryListDao: CountryListDao

    abstract val countryDetailDao: CountryDetailDao

    companion object {
        private const val DATABASE_NAME: String = "countries_db"
        fun build(context: Context): CountriesDatabase = Room.databaseBuilder(
            context.applicationContext,
            CountriesDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
