package io.github.grishaninvyacheslav.lh.network.di

import com.google.gson.Gson
import io.github.grishaninvyacheslav.lh.network.data.repository.CompaniesRepository
import io.github.grishaninvyacheslav.lh.network.data.repository.CompaniesRepositoryImpl
import io.github.grishaninvyacheslav.lh.network.data.source.TestTaskDataSource
import org.koin.dsl.module

val testTaskModelsModule = module {
    single { provideCompaniesRepository(get(), get()) }
}

fun provideCompaniesRepository(
    testTaskApi: TestTaskDataSource,
    gson: Gson
): CompaniesRepository =
    CompaniesRepositoryImpl(
        testTaskApi,
        gson
    )