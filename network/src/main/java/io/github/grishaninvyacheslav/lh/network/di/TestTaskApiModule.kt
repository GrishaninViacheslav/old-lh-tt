package io.github.grishaninvyacheslav.lh.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.grishaninvyacheslav.lh.network.BuildConfig
import io.github.grishaninvyacheslav.lh.network.data.source.TestTaskDataSource
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val testTaskApiModule = module {
    single(named("baseUrl")) { provideBaseUrl() }
    single { provideTestTaskApi(get(named("baseUrl")), get()) }
    single { provideGson() }
}

fun provideBaseUrl(): String = BuildConfig.API_URL

fun provideTestTaskApi(
    baseUrl: String,
    gson: Gson
): TestTaskDataSource {
    val client = OkHttpClient.Builder()
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(TestTaskDataSource::class.java)
}

fun provideGson(): Gson = GsonBuilder().create()