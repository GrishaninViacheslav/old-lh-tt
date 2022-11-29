package io.github.grishaninvyacheslav.lh.network.data.repository

import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyDetailsEntity
import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyPreviewEntity

interface CompaniesRepository {
    suspend fun getCompaniesList(): List<CompanyPreviewEntity>
    suspend fun getCompanyDetails(id: String): CompanyDetailsEntity
}