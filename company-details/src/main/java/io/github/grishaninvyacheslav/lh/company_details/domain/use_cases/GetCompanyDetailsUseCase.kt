package io.github.grishaninvyacheslav.lh.company_details.domain.use_cases

import io.github.grishaninvyacheslav.lh.company_details.data.entity.CompanyDetailsEntity

interface GetCompanyDetailsUseCase {
    suspend operator fun invoke(companyId: String): CompanyDetailsEntity
}