package net.justinas.tesonetapp.withlib.di

import net.justinas.minilist.domain.item.ListRepository
import net.justinas.minitemplate.domain.UserRepository
import net.justinas.tesonetapp.withlib.domain.ListRepositoryRemote
import net.justinas.tesonetapp.withlib.domain.UserRepositoryRemote
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val repositoryModule: Module = module {

    single { ListRepositoryRemote(get()) as ListRepository }
    single { UserRepositoryRemote(get()) as UserRepository }
}