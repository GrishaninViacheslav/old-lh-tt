package io.github.grishaninvyacheslav.lh.company_list.data.entity

import io.github.grishaninvyacheslav.lh.core_ui.presentation.DisplayableItem

data class CompanyItemEntity(
    val id: String,
    val name: String,
    val img: String
) : DisplayableItem