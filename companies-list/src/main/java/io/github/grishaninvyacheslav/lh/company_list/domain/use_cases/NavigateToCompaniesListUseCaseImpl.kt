package io.github.grishaninvyacheslav.lh.company_list.domain.use_cases

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import io.github.grishaninvyacheslav.lh.company_list.presentation.CompaniesListFragment
import io.github.grishaninvyacheslav.lh.navigation.domain.use_cases.NavigateToCompaniesListUseCase

class NavigateToCompaniesListUseCaseImpl(private val router: Router): NavigateToCompaniesListUseCase {
    override operator fun invoke() {
        val explorer = FragmentScreen { CompaniesListFragment.newInstance() }
        router.replaceScreen(explorer)
    }
}