package io.github.grishaninvyacheslav.lh.network.data.repository

import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyDetailsEntity
import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyPreviewEntity
import io.github.grishaninvyacheslav.lh.network.data.source.TestTaskDataSource
import retrofit2.HttpException
import retrofit2.awaitResponse

class CompaniesRepositoryImpl(
    private val testTaskApi: TestTaskDataSource
) : CompaniesRepository {
    override suspend fun getCompaniesList(): List<CompanyPreviewEntity> =
        with(testTaskApi.companiesList().awaitResponse()) {
            when (code()) {
                200 -> {
                    body()?.let {
                        return it
                    } ?: run {
                        throw HttpException(this)
                    }
                }
                else -> throw HttpException(this)
            }
        }


    override suspend fun getCompanyDetails(id: String): CompanyDetailsEntity =
        with(testTaskApi.companyDetails(id).awaitResponse()) {
            when (code()) {
                200 -> {
                    body()?.let {
                        return it
                    } ?: run {
                        throw HttpException(this)
                    }
                }
                else -> throw HttpException(this)
            }
        }
}