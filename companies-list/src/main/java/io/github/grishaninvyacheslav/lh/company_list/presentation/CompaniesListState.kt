package io.github.grishaninvyacheslav.lh.company_list.presentation

import io.github.grishaninvyacheslav.lh.company_list.data.entity.CompanyItemEntity

sealed class CompaniesListState {
    object Loading : CompaniesListState()
    data class Success(val companiesList: List<CompanyItemEntity>) : CompaniesListState()
    data class Error(val error: Throwable) : CompaniesListState()
}