package io.github.grishaninvyacheslav.lh.network.data.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyDetailsEntity
import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyPreviewEntity
import io.github.grishaninvyacheslav.lh.network.data.source.TestTaskDataSource
import retrofit2.HttpException
import retrofit2.awaitResponse


class CompaniesRepositoryImpl(
    private val testTaskApi: TestTaskDataSource,
    private val gson: Gson
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
                        // Экранирование символов ("), которые находятся внутри значений полей.
                        // Это нужно для невалидных запросов с соответствующей ошибкой.
                        // Пример невалидного запроса: https://lifehack.studio/test_task/test.php?id=6
                        val plainText = it.string()
                            .replace("\"", "\\\"")
                            .replace("\\\"id\\\":\\\"", "\"id\":\"")
                            .replace("\\\",\\\"name\\\":\\\"", "\",\"name\":\"")
                            .replace("\\\",\\\"img\\\":\\\"", "\",\"img\":\"")
                            .replace("\\\",\\\"description\\\":\\\"", "\",\"description\":\"")
                            .replace("\\\",\\\"lat\\\"", "\",\"lat\"")
                            .replace("\\\"lon\\\"", "\"lon\"")
                            .replace("\\\"www\\\"", "\"www\"")
                            .replace("\"www\":\\\"", "\"www\":\"")
                            .replace("\\\",\\\"phone\\\":\\\"", "\",\"phone\":\"")
                            .replace("\\\"}]", "\"}]")
                        val typeToken = object : TypeToken<List<CompanyDetailsEntity>>() {}.type
                        val result: List<CompanyDetailsEntity> = gson.fromJson(plainText, typeToken)
                        if (result.isEmpty()) {
                            CompanyDetailsEntity("", "", "", "", 0.0, 0.0, "", "")
                        }
                        return result[0]
                    } ?: run {
                        throw HttpException(this)
                    }
                }
                else -> throw HttpException(this)
            }
        }
}