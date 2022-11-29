package io.github.grishaninvyacheslav.lh.company_list.presentation

import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import io.github.grishaninvyacheslav.lh.company_list.data.entity.CompanyItemEntity
import io.github.grishaninvyacheslav.lh.company_list.databinding.ItemCompanyBinding
import io.github.grishaninvyacheslav.lh.core_ui.presentation.DisplayableItem
import io.github.grishaninvyacheslav.lh.network.BuildConfig

class CompaniesListAdapter : ListDelegationAdapter<List<DisplayableItem>>(
    productImagesListAdapterDelegate()
)

fun productImagesListAdapterDelegate() =
    adapterDelegateViewBinding<CompanyItemEntity, DisplayableItem, ItemCompanyBinding>(
        { layoutInflater, root -> ItemCompanyBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.companyName.text = item.name
            Glide
                .with(binding.companyImage)
                .load(BuildConfig.API_URL + item.img)
                .centerCrop()
                .into(binding.companyImage)
        }
    }