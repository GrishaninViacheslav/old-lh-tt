package io.github.grishaninvyacheslav.lh.company_details.di

import io.github.grishaninvyacheslav.lh.company_details.presentation.CompanyDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val companyDetailsViewModelModule = module {
    viewModel { CompanyDetailsViewModel(get()) }
}