package com.example.cartaodevisitas

import com.example.cartaodevisitas.repository.BusinessCardRepository
import com.example.cartaodevisitas.repository.BusinessCardRepositoryImpl
import com.example.cartaodevisitas.database.BusinessCardDatabase
import com.example.cartaodevisitas.viewmodel.AddBusinessCardViewModel
import com.example.cartaodevisitas.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { BusinessCardDatabase.getDatabase(androidContext())}

    factory<BusinessCardRepository> { BusinessCardRepositoryImpl(get()) }

    viewModel{ MainViewModel(get())}

    viewModel{AddBusinessCardViewModel(get())}
}