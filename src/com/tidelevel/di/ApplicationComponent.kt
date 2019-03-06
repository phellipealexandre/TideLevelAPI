package com.tidelevel.di

import com.tidelevel.service.TideLevelService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {
    fun providesTideLevelService(): TideLevelService
}