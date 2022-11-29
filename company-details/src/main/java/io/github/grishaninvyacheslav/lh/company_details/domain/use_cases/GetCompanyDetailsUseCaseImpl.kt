package io.github.grishaninvyacheslav.lh.company_details.domain.use_cases

import io.github.grishaninvyacheslav.lh.company_details.data.entity.CompanyDetailsEntity
import io.github.grishaninvyacheslav.lh.network.data.repository.CompaniesRepository

class GetCompanyDetailsUseCaseImpl(
    private val companiesRepository: CompaniesRepository
) : GetCompanyDetailsUseCase {
    override suspend operator fun invoke(companyId: String): CompanyDetailsEntity =
        with(companiesRepository.getCompanyDetails(companyId)) {
            CompanyDetailsEntity(
                this.id,
                this.name,
                this.img,
                this.description,
                this.lat,
                this.lon,
                this.www,
                this.phone
            )
        }
}