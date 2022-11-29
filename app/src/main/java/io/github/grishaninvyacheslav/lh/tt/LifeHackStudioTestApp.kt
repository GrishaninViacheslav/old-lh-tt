package io.github.grishaninvyacheslav.lh.tt

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import io.github.grishaninvyacheslav.lh.company_details.di.companyDetailsUseCasesModule
import io.github.grishaninvyacheslav.lh.company_details.di.companyDetailsViewModelModule
import io.github.grishaninvyacheslav.lh.company_list.di.companiesListAdaptersModule
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
                companiesListAdaptersModule,

                companyDetailsUseCasesModule,
                companyDetailsViewModelModule
            )
        }
        MapKitFactory.setApiKey("d15b1b14-c880-4acc-9814-39ea80dcb7e4");
    }
}