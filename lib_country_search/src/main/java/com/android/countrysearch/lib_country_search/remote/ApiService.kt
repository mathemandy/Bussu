package com.android.countrysearch.lib_country_search.remote

import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

internal interface ApiService {

    @GET("name/{name}")
    suspend fun searchCountries(@Path("name") params: String, @Query("fields") field: String): List<CountryRemoteModel>?

    @GET("all")
    suspend fun fetchAllCountries(@Query("fields") field : String): List<CountryRemoteModel>?

}
