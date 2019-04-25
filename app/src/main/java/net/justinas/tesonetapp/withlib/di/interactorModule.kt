package net.justinas.tesonetapp.withlib.di

import net.justinas.minilist.domain.item.GetListItemsInteractor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val interaqctorModule: Module = module {

    single { GetListItemsInteractor(get()) }
}