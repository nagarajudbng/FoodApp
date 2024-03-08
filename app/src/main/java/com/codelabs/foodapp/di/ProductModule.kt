import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.codelabs.foodapp.core.util.Constants
import com.codelabs.foodapp.feature_auth.data.remote.LoginApi
import com.codelabs.foodapp.feature_prod.data.remote.ProductsAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ProductModule{
    companion object{
        fun getApi(app: Context): ProductsAPI {
            return Retrofit.Builder()
                .baseUrl(LoginApi.BASE_URL)
                .client(getOkHttpClient(getSharedPref(app)))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductsAPI::class.java)
        }

        fun getOkHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor {
                    val token = sharedPreferences.getString(Constants.KEY_JWT_TOKEN, "")
                    val modifiedRequest = it.request().newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()
                    it.proceed(modifiedRequest)
                }
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