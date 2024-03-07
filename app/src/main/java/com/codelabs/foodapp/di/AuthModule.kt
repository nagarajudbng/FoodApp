import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.codelabs.foodapp.core.util.Constants
import com.codelabs.foodapp.feature_auth.data.remote.LoginApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//package com.codelabs.foodapp.di
//
//import android.content.SharedPreferences
//import com.plcoding.socialnetworktwitch.feature_auth.data.remote.AuthApi
//import com.plcoding.socialnetworktwitch.feature_auth.data.repository.AuthRepositoryImpl
//import com.plcoding.socialnetworktwitch.feature_auth.domain.repository.AuthRepository
//import com.plcoding.socialnetworktwitch.feature_auth.domain.use_case.AuthenticateUseCase
//import com.plcoding.socialnetworktwitch.feature_auth.domain.use_case.LoginUseCase
//import com.plcoding.socialnetworktwitch.feature_auth.domain.use_case.RegisterUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AuthModule {
//
//    @Provides
//    @Singleton
//    fun provideAuthApi(client: OkHttpClient): AuthApi {
//        return Retrofit.Builder()
//            .baseUrl(AuthApi.BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(AuthApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(api: AuthApi, sharedPreferences: SharedPreferences): AuthRepository {
//        return AuthRepositoryImpl(api, sharedPreferences)
//    }
//
//    @Provides
//    @Singleton
//    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
//        return RegisterUseCase(repository)
//    }
//
//    @Provides
//    @Singleton
//    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
//        return LoginUseCase(repository)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthenticationUseCase(repository: AuthRepository): AuthenticateUseCase {
//        return AuthenticateUseCase(repository)
//    }
//}

class AuthModule{
    companion object{
        fun getApi(app: Context): LoginApi {
            return Retrofit.Builder()
                .baseUrl(LoginApi.BASE_URL)
                .client(getOkHttpClient(getSharedPref(app)))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginApi::class.java)
        }

        fun getOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
            return OkHttpClient.Builder()
//                .addInterceptor {
//                    val token = sharedPreferences.getString(Constants.KEY_JWT_TOKEN, "")
//                    val modifiedRequest = it.request().newBuilder()
//                        .addHeader("Authorization", "Bearer $token")
//                        .build()
//                    it.proceed(modifiedRequest)
//                }
                .addInterceptor(Interceptor { chain ->
                    val originalRequest: Request = chain.request()
                    Log.d("Host",originalRequest.url.host)
                    val newRequest: Request = originalRequest.newBuilder()
                        .header("Host", originalRequest.url.host)
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Accept", "*/*")
                        .build()
                    chain.proceed(newRequest)
                })
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )

                .build()
        }
        fun getSharedPref(app: Context): SharedPreferences {
            return app.getSharedPreferences(
                Constants.SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            )
        }
    }

}