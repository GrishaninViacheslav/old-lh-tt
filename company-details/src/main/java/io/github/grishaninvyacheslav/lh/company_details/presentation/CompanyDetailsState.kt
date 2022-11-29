package io.github.grishaninvyacheslav.lh.company_details.presentation

import io.github.grishaninvyacheslav.lh.company_details.data.entity.CompanyDetailsEntity

sealed class CompanyDetailsState {
    object Loading : CompanyDetailsState()
    data class Success(val companyDetails: CompanyDetailsEntity) : CompanyDetailsState()
    data class Error(val error: Throwable) : CompanyDetailsState()
}