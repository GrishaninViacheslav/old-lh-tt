package io.github.grishaninvyacheslav.lh.network.data.source

import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyDetailsEntity
import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyPreviewEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TestTaskDataSource {
    @GET("test.php")
    fun companiesList(): Call<List<CompanyPreviewEntity>>

    @GET("test.php")
    fun companyDetails(@Query("id") id: String): Call<CompanyDetailsEntity>
}