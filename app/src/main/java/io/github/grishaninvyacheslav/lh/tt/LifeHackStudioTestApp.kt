package io.github.grishaninvyacheslav.lh.tt

import android.app.Application
import io.github.grishaninvyacheslav.lh.company_list.di.companiesListUseCasesModule
import io.github.grishaninvyacheslav.lh.company_list.di.companiesListViewModelsModule
import io.github.grishaninvyacheslav.lh.navigation.domain.di.navigationModule
import io.github.grishaninvyacheslav.lh.network.di.testTaskApiModule
import io.github.grishaninvyacheslav.lh.network.di.testTaskModelsModule
import io.github.grishaninvyacheslav.lh.tt.di.mainViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LifeHackStudioTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LifeHackStudioTestApp)
            modules(
                testTaskApiModule,
                testTaskModelsModule,

                navigationModule,

                mainViewModelsModule,

                companiesListUseCasesModule,
                companiesListViewModelsModule,
            )
        }
    }
}