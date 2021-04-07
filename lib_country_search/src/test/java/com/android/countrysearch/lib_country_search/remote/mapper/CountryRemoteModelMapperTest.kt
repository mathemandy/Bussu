package com.android.countrysearch.lib_country_search.remote.mapper

import com.android.countrysearch.lib_country_search.data.model.CountryEntity
import com.android.countrysearch.lib_country_search.remote.DummyData
import com.android.countrysearch.lib_country_search.remote.model.response.CountryRemoteModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CountryRemoteModelMapperTest {

    private val countryRemoteModelMapper = CountryRemoteModelMapper()

    @Test
    fun `check that mapFromModel returns correct data`() {
        val countryRemoteModel: CountryRemoteModel = DummyData.countryRemoteModel
        val countryEntity: CountryEntity =
            countryRemoteModelMapper.mapFromModel(countryRemoteModel)
        assertThat(countryRemoteModel.name).isEqualTo(countryEntity.name)
        assertThat(countryRemoteModel.flag).isEqualTo(countryEntity.flag)
    }
}
