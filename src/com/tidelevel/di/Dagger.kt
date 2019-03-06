package com.tidelevel.di

object Dagger {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }
}