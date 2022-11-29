package io.github.grishaninvyacheslav.lh.company_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.grishaninvyacheslav.lh.company_list.domain.use_cases.GetCompaniesListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompaniesListViewModel(
    private val getCompaniesListUseCase: GetCompaniesListUseCase
) : ViewModel() {
    private val mutableCompaniesListState: MutableLiveData<CompaniesListState> = MutableLiveData()
    val companiesListState: LiveData<CompaniesListState>
        get() {
            if (mutableCompaniesListState.value != null) {
                return mutableCompaniesListState
            }
            updateListState()
            return mutableCompaniesListState
        }

    fun updateListState() {
        mutableCompaniesListState.value = CompaniesListState.Loading
        viewModelScope.launch(Dispatchers.IO + companiesListExceptionHandler) {
            mutableCompaniesListState.postValue(
                CompaniesListState.Success(getCompaniesListUseCase())
            )
        }
    }

    private val companiesListExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        mutableCompaniesListState.postValue(CompaniesListState.Error(throwable))
    }
}