package io.github.grishaninvyacheslav.lh.company_list.domain.use_cases

import io.github.grishaninvyacheslav.lh.company_list.data.entity.CompanyItemEntity

interface GetCompaniesListUseCase {
    suspend operator fun invoke(): List<CompanyItemEntity>
}