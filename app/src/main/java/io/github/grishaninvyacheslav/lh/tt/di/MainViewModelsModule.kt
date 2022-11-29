package io.github.grishaninvyacheslav.lh.tt.di

import io.github.grishaninvyacheslav.lh.tt.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelsModule = module {
    viewModel { MainViewModel(get()) }
}