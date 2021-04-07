package com.android.countrysearch.core.di

import com.android.countrysearch.core.executor.PostExecutionThreadImpl
import com.android.countrysearch.lib_country_search.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}
