package com.softdough.grow

import android.content.Intent.CATEGORY_PREFERENCE
import com.softdough.grow.data.repository.*
import com.softdough.grow.datasource.local.*
import com.softdough.grow.datasource.remote.*
import com.softdough.grow.domain.usecase.*
import com.softdough.grow.presentation.GlobalApplication
import com.softdough.grow.presentation.ui.CreateRoutine.CreateRoutineViewModel
import com.softdough.grow.presentation.ui.Login.LoginViewModel
import com.softdough.grow.presentation.ui.Routine.custom.CustomViewModel
import com.softdough.grow.presentation.ui.Routine.recommend.RecommendViewModel
import com.softdough.grow.presentation.ui.Splash.SplashViewModel
import com.softdough.grow.presentation.ui.UserInfo.UserInfoViewModel
import com.softdough.grow.presentation.ui.Workout.WorkoutViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { UserInfoViewModel(get(), get()) }

    viewModel { RecommendViewModel(get(), get(), get()) }
    viewModel { CustomViewModel(get(), get(), get()) }

    viewModel { CreateRoutineViewModel(get(), get(), get()) }

    viewModel { WorkoutViewModel(get(), get()) }
}

val usecaseModule: Module = module {
    factory { AuthorizeUseCase(authorizeRepository = get()) }
    factory { AccountUseCase(get(named("crash_account_repository"))) }
    factory { CategoryUseCase(get(named("crash_category_repository"))) }
    factory { RoutineUseCase(get(named("crash_routine_repository"))) }
    factory { ExerciseUseCase(get(named("crash_exercise_repository"))) }
    factory { SetInfoUseCase(get(named("crash_set_info_repository"))) }
}

val repositoryModule: Module = module {
    single { AuthorizeRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single(named("crash_account_repository")) {
        AccountRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    single(named("crash_category_repository")) {
        CategoryRepositoryImpl(
            localDataSource = get(named("crash_category_local_data")),
            remoteDataSource = get()
        )
    }
    single(named("crash_routine_repository")) {
        RoutineRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    single(named("crash_exercise_repository")) {
        ExerciseRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    single(named("crash_set_info_repository")) {
        SetInfoRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}

val dataSourceModule: Module = module {
    single { AuthorizeRemoteDataSourceImpl(get()) }
    single { AuthorizeLocalDataSourceImpl(pref = get(named(AUTHORIZE))) }

    single { AccountRemoteDataSourceImpl(get()) }
    single { AccountLocalDataSourceImpl(pref = get(named(ACCOUNT))) }

    single { CategoryRemoteDataSourceImpl(get()) }

    single(named("crash_category_local_data")) {
        CategoryLocalDataSourceImpl(pref = get(named(CATEGORY)))
    }

    single { RoutineRemoteDataSourceImpl(get()) }
    single { RoutineLocalDataSourceImpl(pref = get(named(ROUTINE))) }

    single { ExerciseRemoteDataSourceImpl(get()) }
    single { ExerciseLocalDataSourceImpl(pref = get(named(EXERCISE))) }

    single { SetInfoRemoteDataSourceImpl(get()) }
    single { SetInfoLocalDataSourceImpl(pref = get(named(SETINFO))) }
}

val remoteModule: Module = module {
    single { authApi }
    single { accountApi }
    single { categoryApi }
    single { routineApi }
    single { exerciseApi }
    single { setInfoApi }
}

val localModule: Module = module {
    single(named(AUTHORIZE)) { SharedPreference(androidContext(), AUTHORIZE) }
    single(named(ACCOUNT)) { SharedPreference(androidContext(), ACCOUNT) }
    single(named(CATEGORY)) { SharedPreference(androidContext(), CATEGORY) }
    single(named(ROUTINE)) { SharedPreference(androidContext(), ROUTINE) }
    single(named(EXERCISE)) { SharedPreference(androidContext(), EXERCISE) }
    single(named(SETINFO)) { SharedPreference(androidContext(), SETINFO) }
}

//private const val BASE_URL = "http://172.16.208.79:8080/"
private const val BASE_URL = "http://35.213.99.68:8080/"

private val LogInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

//private val client = OkHttpClient.Builder().addInterceptor(LogInterceptor).build()

private val client = OkHttpClient.Builder().apply {
    addInterceptor { chain ->
        val original = chain.request()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .header(
                "Authorization",
                "Bearer " + GlobalApplication.jwtToken
            ) // <-- this is the important line

        val request = requestBuilder.build()
        chain.proceed(request)
    }

    addNetworkInterceptor(LogInterceptor)

    connectTimeout(30, TimeUnit.SECONDS)
    readTimeout(30, TimeUnit.SECONDS)
}.build()

private val retrofit: Retrofit =
    Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Call 객체가 아닌 RxJava 형태로 받기 위함
        .build()


private val authApi: AuthApi = retrofit.create(AuthApi::class.java)
private val accountApi: AccountApi = retrofit.create(AccountApi::class.java)
private val categoryApi: CategoryApi = retrofit.create(CategoryApi::class.java)
private val routineApi: RoutineApi = retrofit.create(RoutineApi::class.java)
private val exerciseApi: ExerciseApi = retrofit.create(ExerciseApi::class.java)
private val setInfoApi: SetInfoApi = retrofit.create(SetInfoApi::class.java)

private const val AUTHORIZE = "AUTHORIZE"
private const val ACCOUNT = "ACCOUNT"
private const val CATEGORY = "CATEGORY"
private const val ROUTINE = "ROUTINE"
private const val EXERCISE = "EXERCISE"
private const val SETINFO = "SETINFO"
