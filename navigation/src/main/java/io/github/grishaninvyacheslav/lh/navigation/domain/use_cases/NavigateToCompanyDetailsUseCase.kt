package io.github.grishaninvyacheslav.lh.navigation.domain.use_cases

interface NavigateToCompaniesDetailsUseCase {
    operator fun invoke(companyId: String)
}