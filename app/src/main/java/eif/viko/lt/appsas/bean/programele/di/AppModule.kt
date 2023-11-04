package eif.viko.lt.appsas.bean.programele.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eif.viko.lt.appsas.bean.programele.data.AuthRepositoryImpl
import eif.viko.lt.appsas.bean.programele.data.GoogleSignInService
import eif.viko.lt.appsas.bean.programele.domain.repositories.AuthRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


//    @Provides
//    @Singleton
//    fun provideSharedPref(app: Application): SharedPreferences {
//        return app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
//    }
//

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(GoogleSignInService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    @Provides
    @Singleton
    fun providesGoogleSignInService(retrofitBuilder: Retrofit.Builder): GoogleSignInService {
        return retrofitBuilder
            .build()
            .create(GoogleSignInService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: GoogleSignInService,
    ): AuthRepository {
        return AuthRepositoryImpl(api)
    }



//    @Provides
//    @Singleton
//    fun providesRetrofitBuilder(): Retrofit.Builder {
//        return Retrofit.Builder()
//            .baseUrl(AuthApi.BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHTTPClient(authInterceptor: OAuthInterceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthApi(retrofitBuilder: Retrofit.Builder): AuthApi {
//        return retrofitBuilder
//            .build()
//            .create(AuthApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideShopApi(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): ShopApi {
//        return retrofitBuilder
//            .client(okHttpClient)
//            .build()
//            .create(ShopApi::class.java)
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(
//        api: AuthApi,
//        prefs: SharedPreferences
//    ): AuthRepository {
//        return AuthRepositoryImpl(api, prefs)
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideTimetableApi(): TimetableApi {
//        return Retrofit.Builder()
//            .baseUrl(TimetableApi.BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create(TimetableApi::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideTimetableRepository(
//        api: TimetableApi,
//        db: GroupDatabase
//    ): TimetableRepository {
//        return TimetableRepositoryImpl(api, db.dao)
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideGroupDatabase(app: Application): GroupDatabase {
//        return Room.databaseBuilder(
//            app,
//            GroupDatabase::class.java,
//            "db_timetable"
//        ).build()
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideTimetableUseCases(
//        repository: TimetableRepository
//    ): TimetableUseCases {
//        return TimetableUseCases(getGroupsUseCase = GetGroupsUseCase(repository))
//    }
//
//    // =============== SHOP OBJECTS ==============================================
//    @Provides
//    @Singleton
//    fun provideShopRepository(
//        api: ShopApi,
//    ): ShopRepository {
//        return ShopRepositoryImpl(api)
//    }
//
//    @Singleton
//    @Provides
//    fun provideShopUseCases(
//        repository: ShopRepository
//    ): ShopUseCases {
//        return ShopUseCases(
//            getCategoriesUseCase = GetCategoriesUseCase(repository),
//            getProductsByCategoryUseCase = GetProductsByCategoryUseCase(repository)
//        )
//    }


}