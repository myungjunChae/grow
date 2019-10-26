package com.softdough.grow

import com.softdough.grow.data.repository.AccountRepositoryImpl
import com.softdough.grow.data.repository.CategoryRepositoryImpl
import com.softdough.grow.data.repository.RoutineRepositoryImpl
import com.softdough.grow.datasource.local.AccountLocalDataSourceImpl
import com.softdough.grow.datasource.local.CategoryLocalDataSourceImpl
import com.softdough.grow.datasource.local.RoutineLocalDataSourceImpl
import com.softdough.grow.datasource.local.SharedPreference
import com.softdough.grow.datasource.remote.*
import com.softdough.grow.domain.usecase.AccountUseCase
import com.softdough.grow.domain.usecase.CategoryRoutinesUseCase
import com.softdough.grow.domain.usecase.CategoryUseCase
import com.softdough.grow.domain.usecase.RoutineUseCase
import com.softdough.grow.presentation.routine.custom.CustomViewModel
import com.softdough.grow.presentation.routine.recommend.RecommendViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun injectionFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            usecaseModule,
            repositoryModule,
            dataSourceModule,
            remoteModule,
            localModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel { RecommendViewModel(get(), get()) }
    viewModel { CustomViewModel(get(), get()) }
}

val usecaseModule: Module = module {
    factory { AccountUseCase(accountRepository = get()) }
    factory { CategoryRoutinesUseCase(categoryRepository = get(), routineRepository = get()) }
    factory { CategoryUseCase(categoryRepository = get()) }
    factory { RoutineUseCase(routineRepository = get()) }
}

val repositoryModule: Module = module {
    single { AccountRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single { CategoryRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single { RoutineRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
}

val dataSourceModule: Module = module {
    single { AccountRemoteDataSourceImpl(api = accountApi) }
    single { AccountLocalDataSourceImpl(sharedPreference = get(ACCOUNT_PREFERENCE)) }

    single { CategoryRemoteDataSourceImpl(api = categoryApi) }
    single { CategoryLocalDataSourceImpl(sharedPreference = get(CATEGORY_PREFERENCE)) }

    single { RoutineRemoteDataSourceImpl(api = routineApi) }
    single { RoutineLocalDataSourceImpl(sharedPreference = get(ROUTINE_PREFERENCE)) }
}

val remoteModule: Module = module {
    single { accountApi }
    single { categoryApi }
    single { routineApi }
}

val localModule: Module = module {
    single(ACCOUNT_PREFERENCE) { SharedPreference(androidContext(), "default") }
    single(CATEGORY_PREFERENCE) { SharedPreference(androidContext(), "default") }
    single(ROUTINE_PREFERENCE) { SharedPreference(androidContext(), "default") }
}

private val BASE_URL = ""

private val retrofit: Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // Why this line needed
        .build()

private val accountApi: AccountApi = retrofit.create(AccountApi::class.java)
private val categoryApi: CategoryApi = retrofit.create(CategoryApi::class.java)
private val routineApi: RoutineApi = retrofit.create(RoutineApi::class.java)

private val ACCOUNT_PREFERENCE = named("ACCOUNT_PREFERENCE")
private val CATEGORY_PREFERENCE = named("CATEGORY_PREFERENCE")
private val ROUTINE_PREFERENCE = named("ROUTINE_PREFERENCE")