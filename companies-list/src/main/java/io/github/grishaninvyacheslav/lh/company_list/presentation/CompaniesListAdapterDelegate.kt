package io.github.grishaninvyacheslav.lh.company_list.presentation

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.github.grishaninvyacheslav.lh.company_list.R
import io.github.grishaninvyacheslav.lh.company_list.data.entity.CompanyItemEntity
import io.github.grishaninvyacheslav.lh.company_list.databinding.ItemCompanyBinding
import io.github.grishaninvyacheslav.lh.core_ui.presentation.DisplayableItem
import io.github.grishaninvyacheslav.lh.navigation.domain.use_cases.NavigateToCompaniesDetailsUseCase
import io.github.grishaninvyacheslav.lh.network.BuildConfig

class CompaniesListAdapterDelegate(
    private val navigateToCompanyDetailsUseCase: NavigateToCompaniesDetailsUseCase
) {
    operator fun invoke() =
        adapterDelegateViewBinding<CompanyItemEntity, DisplayableItem, ItemCompanyBinding>(
            { layoutInflater, root -> ItemCompanyBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                binding.root.setOnClickListener {
                    navigateToCompanyDetailsUseCase(item.id)
                }
                binding.companyName.text = item.name
                Glide
                    .with(binding.companyImage)
                    .load(BuildConfig.API_URL + item.img)
                    .placeholder(R.drawable.ic_no_company_image)
                    .centerCrop()
                    .into(binding.companyImage)
            }
        }
}
