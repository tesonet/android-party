package net.justinas.tesonetapp.withlib.di

import net.justinas.tesonetapp.withlib.view.ListViewModel
import net.justinas.tesonetapp.withlib.view.LoginViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule: Module = module {

    viewModel { LoginViewModel(get()) }
    viewModel { ListViewModel(get()) }
}