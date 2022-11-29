package io.github.grishaninvyacheslav.lh.company_list.presentation

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import io.github.grishaninvyacheslav.lh.company_list.R
import io.github.grishaninvyacheslav.lh.company_list.data.entity.CompanyItemEntity
import io.github.grishaninvyacheslav.lh.company_list.databinding.FragmentCompaniesListBinding
import io.github.grishaninvyacheslav.lh.core_ui.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs
import org.koin.android.ext.android.inject

class CompaniesListFragment :
    BaseFragment<FragmentCompaniesListBinding>(FragmentCompaniesListBinding::inflate) {
    companion object {
        fun newInstance() = CompaniesListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initView()
    }

    private fun initObservers() {
        viewModel.companiesListState.observe(viewLifecycleOwner) { renderCompaniesListState(it) }
    }

    private fun initView() = with(binding) {
        retryAction.setOnClickListener {
            viewModel.updateListState()
        }
    }

    private fun renderCompaniesListState(state: CompaniesListState) = when (state) {
        CompaniesListState.Loading -> {
            setProgressBarStatus(true)
            setRetryError(null)
        }
        is CompaniesListState.Success -> {
            setProgressBarStatus(false)
            setRetryError(null)
            initCompanyList(state.companiesList)
        }
        is CompaniesListState.Error -> {
            setProgressBarStatus(false)
            setRetryError(state.error.message)
            showErrorMessage(state.error)
        }
    }

    private fun initCompanyList(categories: List<CompanyItemEntity>) = with(binding) {
        companiesList.adapter = categoriesAdapter.apply { items = categories }
        companiesList.offscreenPageLimit = 3
        setPageTransformer()
    }

    private fun setProgressBarStatus(isInProgress: Boolean) = with(binding) {
        progressBar.isVisible = isInProgress
    }

    private fun setRetryError(errorMessage: String?) = with(binding) {
        errorMessage?.let {
            retry.isVisible = true
            retryErrorMessage.text = it
        } ?: run {
            retry.isVisible = false
            retryErrorMessage.text = ""
        }
    }

    private fun setPageTransformer() {
        binding.companiesList.setPageTransformer { page, position ->
            val pageMargin = resources.getDimensionPixelOffset(R.dimen.view_page_margin).toFloat()
            val pageOffset = resources.getDimensionPixelOffset(R.dimen.view_page_offset).toFloat()
            val offscreenPageScale = TypedValue().apply {
                resources.getValue(
                    R.dimen.view_pager_offscreen_page_scale, this, true
                )
            }.float
            val myOffset: Float = position * -(2 * pageOffset + pageMargin)
            page.scaleY = 1 - abs(position) * (1F - offscreenPageScale)
            when {
                position < -1 -> page.translationX = -myOffset
                position <= 1 -> page.translationX = myOffset
                else -> page.translationX = myOffset
            }
        }
    }

    private val categoriesAdapter: CompaniesListAdapter by inject()
    private val viewModel: CompaniesListViewModel by viewModel()
}