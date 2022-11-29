package io.github.grishaninvyacheslav.lh.company_list.di

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.lh.company_list.domain.use_cases.GetCompaniesListUseCase
import io.github.grishaninvyacheslav.lh.company_list.domain.use_cases.GetCompaniesListUseCaseImpl
import io.github.grishaninvyacheslav.lh.company_list.domain.use_cases.NavigateToCompaniesListUseCaseImpl
import io.github.grishaninvyacheslav.lh.navigation.domain.use_cases.NavigateToCompaniesListUseCase
import io.github.grishaninvyacheslav.lh.network.data.repository.CompaniesRepository
import org.koin.dsl.module

val companiesListUseCasesModule = module {
    factory { provideNavigateToCompaniesList(get()) }
    factory { provideGetCompaniesList(get()) }
}

fun provideNavigateToCompaniesList(router: Router): NavigateToCompaniesListUseCase =
    NavigateToCompaniesListUseCaseImpl(router)

fun provideGetCompaniesList(companiesRepository: CompaniesRepository): GetCompaniesListUseCase =
    GetCompaniesListUseCaseImpl(companiesRepository)