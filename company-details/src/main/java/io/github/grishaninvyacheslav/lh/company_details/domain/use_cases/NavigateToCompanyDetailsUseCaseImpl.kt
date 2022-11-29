package io.github.grishaninvyacheslav.lh.company_details.domain.use_cases

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.github.grishaninvyacheslav.lh.company_details.presentation.CompanyDetailsFragment
import io.github.grishaninvyacheslav.lh.navigation.domain.use_cases.NavigateToCompaniesDetailsUseCase

class NavigateToCompanyDetailsUseCaseImpl(private val router: Router):
    NavigateToCompaniesDetailsUseCase {
    override operator fun invoke(companyId: String) {
        val explorer = FragmentScreen { CompanyDetailsFragment.newInstance(companyId) }
        router.navigateTo(explorer)
    }
}