package com.rilodev.lumutapps.core.di

import android.content.Context
import com.rilodev.lumutapps.core.data.remote.network.ApiConfig
import com.rilodev.lumutapps.core.data.LumutAppsRepository
import com.rilodev.lumutapps.core.data.remote.RemoteDataSource

object Injection {
    fun provideStoryAppRepository(context: Context): LumutAppsRepository {
        val remoteDataSource = RemoteDataSource(ApiConfig.getApiService())
        return LumutAppsRepository(remoteDataSource)
    }
}