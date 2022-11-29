package io.github.grishaninvyacheslav.lh.network.data.source

import io.github.grishaninvyacheslav.lh.network.data.entity.CompanyPreviewEntity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface TestTaskDataSource {
    @GET("test.php")
    fun companiesList(): Call<List<CompanyPreviewEntity>>

    /**
     * В Call передаю именно ResponseBody, а не конечный data class, так как
     * данные запросы моежт вернуть невалидный JSON, в котором в занчениях полей
     * могут встречаться неэкранированные символы ("). Пример невалидного ответа:
     * https://lifehack.studio/test_task/test.php?id=6
     * Чтобы исправить эту ошибку API, парсинг ответов я проделываю
     * самостоятельно в репозитории CompaniesRepositoryImpl
     */
    @GET("test.php")
    fun companyDetails(@Query("id") id: String): Call<ResponseBody>
}