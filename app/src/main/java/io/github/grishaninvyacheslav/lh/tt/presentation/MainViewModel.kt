package io.github.grishaninvyacheslav.lh.tt.presentation

import androidx.lifecycle.ViewModel
import io.github.grishaninvyacheslav.lh.navigation.domain.use_cases.NavigateToCompaniesListUseCase

class MainViewModel(
    private val navigateToCompaniesListUseCase: NavigateToCompaniesListUseCase
) : ViewModel() {
    fun navigateToCart() = navigateToCompaniesListUseCase()
}