package com.rosdyana.tomtommart.di

import com.rosdyana.tomtommart.data.DataSource
import com.rosdyana.tomtommart.data.Repository
import com.rosdyana.tomtommart.model.DataBase
import org.koin.dsl.module

val dataModule = module {
    single { DataBase.getInstance() }
    factory { DataSource() }
    single { Repository(get(), get()) }
}