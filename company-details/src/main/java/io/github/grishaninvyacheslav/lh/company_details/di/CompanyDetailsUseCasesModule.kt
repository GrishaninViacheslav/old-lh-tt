package io.github.grishaninvyacheslav.lh.company_details.di

import com.github.terrakok.cicerone.Router
import io.github.grishaninvyacheslav.lh.company_details.domain.use_cases.GetCompanyDetailsUseCase
import io.github.grishaninvyacheslav.lh.company_details.domain.use_cases.GetCompanyDetailsUseCaseImpl
import io.github.grishaninvyacheslav.lh.company_details.domain.use_cases.NavigateToCompanyDetailsUseCaseImpl
import io.github.grishaninvyacheslav.lh.navigation.domain.use_cases.NavigateToCompaniesDetailsUseCase
import io.github.grishaninvyacheslav.lh.network.data.repository.CompaniesRepository
import org.koin.dsl.module

val companyDetailsUseCasesModule = module {
    factory { provideNavigateToCompanyDetails(get()) }
    factory { provideGetCompanyDetails(get()) }
}

fun provideNavigateToCompanyDetails(router: Router): NavigateToCompaniesDetailsUseCase =
    NavigateToCompanyDetailsUseCaseImpl(router)

fun provideGetCompanyDetails(companiesRepository: CompaniesRepository): GetCompanyDetailsUseCase =
    GetCompanyDetailsUseCaseImpl(companiesRepository)