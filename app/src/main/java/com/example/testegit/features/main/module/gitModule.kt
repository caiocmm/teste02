package com.example.testegit.features.main.module

import com.example.testegit.core.db.DatabaseProvider
import com.example.testegit.core.network.createWebService
import com.example.testegit.features.main.data.MainService
import com.example.testegit.features.main.data.repository.MainRepository
import com.example.testegit.features.main.data.repository.MainRepositoryImpl
import com.example.testegit.features.main.viewModel.MainViewModel
import org.koin.dsl.module

@JvmField
val gitModule = module {
    factory<MainRepository> {
        MainRepositoryImpl(get(), get())
    }
    factory {
        MainViewModel(get())
    }
    single { DatabaseProvider.provideAppDb(get()) }
    single { DatabaseProvider.provideFieldsDao(get()) }
    single<MainService> { createWebService() }
}