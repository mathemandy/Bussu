package com.android.countrysearch.name_search.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.navigation.NavigationDispatcher
import com.android.countrysearch.name_search.navigation.NavigationDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
interface NavigationModule {

    @get:Binds
    val NavigationDispatcherImpl.navigationDispatcher: NavigationDispatcher

    companion object {
        @Provides
        fun provideNavController(activity: Activity): NavController =
            activity.findNavController(R.id.mainHostFragment)
    }
}
