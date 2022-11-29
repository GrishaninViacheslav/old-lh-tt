package io.github.grishaninvyacheslav.lh.company_list.domain.use_cases

import io.github.grishaninvyacheslav.lh.company_list.data.entity.CompanyItemEntity
import io.github.grishaninvyacheslav.lh.network.data.repository.CompaniesRepository

class GetCompaniesListUseCaseImpl(
    private val companiesRepository: CompaniesRepository
) : GetCompaniesListUseCase {
    override suspend operator fun invoke(): List<CompanyItemEntity> =
        companiesRepository.getCompaniesList().map { CompanyItemEntity(it.id, it.name, it.img) }
}