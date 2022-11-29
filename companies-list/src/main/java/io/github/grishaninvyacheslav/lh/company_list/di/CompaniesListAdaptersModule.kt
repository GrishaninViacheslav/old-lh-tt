package io.github.grishaninvyacheslav.lh.company_list.di

import io.github.grishaninvyacheslav.lh.company_list.presentation.CompaniesListAdapter
import io.github.grishaninvyacheslav.lh.company_list.presentation.CompaniesListAdapterDelegate
import io.github.grishaninvyacheslav.lh.navigation.domain.use_cases.NavigateToCompaniesDetailsUseCase
import org.koin.dsl.module

val companiesListAdaptersModule = module {
    single { provideCompanyListAdapter(get()) }
}

fun provideCompanyListAdapter(
    navigateToCompaniesDetailsUseCase: NavigateToCompaniesDetailsUseCase
): CompaniesListAdapter =
    CompaniesListAdapter(
        CompaniesListAdapterDelegate(
            navigateToCompaniesDetailsUseCase
        )
    )