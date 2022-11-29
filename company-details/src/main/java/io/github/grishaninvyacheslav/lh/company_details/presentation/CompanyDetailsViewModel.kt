package io.github.grishaninvyacheslav.lh.company_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.grishaninvyacheslav.lh.company_details.domain.use_cases.GetCompanyDetailsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyDetailsViewModel(
    private val getCompanyDetailsUseCase: GetCompanyDetailsUseCase
) : ViewModel() {
    private val mutableCompanyDetailsState: MutableLiveData<CompanyDetailsState> = MutableLiveData()
    fun getCompaniesListState(companyId: String): LiveData<CompanyDetailsState> {
        if (mutableCompanyDetailsState.value != null) {
            return mutableCompanyDetailsState
        }
        updateCompanyDetailsState(companyId)
        return mutableCompanyDetailsState
    }

    fun updateCompanyDetailsState(companyId: String){
        mutableCompanyDetailsState.value = CompanyDetailsState.Loading
        viewModelScope.launch(Dispatchers.IO + companyDetailsExceptionHandler) {
            mutableCompanyDetailsState.postValue(
                CompanyDetailsState.Success(getCompanyDetailsUseCase(companyId))
            )
        }
    }

    private val companyDetailsExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        mutableCompanyDetailsState.postValue(CompanyDetailsState.Error(throwable))
    }
}