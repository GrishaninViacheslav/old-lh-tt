package io.github.grishaninvyacheslav.lh.company_list.di

import io.github.grishaninvyacheslav.lh.company_list.presentation.CompaniesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val companiesListViewModelsModule = module {
    viewModel { CompaniesListViewModel(get()) }
}