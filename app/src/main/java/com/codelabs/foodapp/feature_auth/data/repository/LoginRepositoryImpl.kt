package com.codelabs.foodapp.feature_auth.data.repository

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.codelabs.foodapp.R
import com.codelabs.foodapp.core.util.Constants
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.core.util.SimpleResource
import com.codelabs.foodapp.core.util.UiText
import com.codelabs.foodapp.feature_auth.data.remote.LoginApi
import com.codelabs.foodapp.feature_auth.data.remote.request.LoginRequest
import com.codelabs.foodapp.feature_auth.data.remote.request.LoginRequestParent
import com.codelabs.foodapp.feature_auth.data.remote.response.LoginResponseParent
import com.codelabs.foodapp.feature_auth.domain.repository.LoginRepository
import retrofit2.HttpException
import java.io.IOException

class LoginRepositoryImpl(
    private val api: LoginApi,
    private val sharedPreferences:SharedPreferences
):LoginRepository {


//     suspend fun login2(email: String, password: String): Flow<SimpleResource> {
//
//        return flow{
//            val request = LoginRequest(email,password)
//            val loginRequest = LoginRequestParent(request)
//            val response = api.login(loginRequest)
////            response.message?.let { Log.d("Response", it) }
//        }
//    }

     override suspend fun login(email: String, password: String): SimpleResource {
         /*
         val request = LoginRequest(email, password)
         val loginRequest = LoginRequestParent(request)
         val response = api.login(loginRequest)
         response?.let { Log.d("Response response", it.body().toString()) }
         return Resource.Success(Unit)
         */
         val request = LoginRequest(email, password)
         val simpleResource:Resource.Success<LoginResponseParent>
         return try{

            val loginRequest = LoginRequestParent(request)
            val response = api.login(loginRequest)
            if(response.isSuccessful ){
                Log.d("Response if", response.body().toString())

                 simpleResource = Resource.Success<LoginResponseParent>(response.body())
                simpleResource.data?.LoginResponse?.token?.let {
                    Log.d("Response simpleResource",
                        it
                    )
                }
                sharedPreferences.edit()
                    .putString(Constants.KEY_JWT_TOKEN,
                        simpleResource.data?.LoginResponse?.token
                    )
                    .apply()
                Resource.Success(Unit)
            }else {
                /*
                    response.message()?.let { msg->
                        Resource.Error(uiText = UiText.DynamicString(msg))
                    }?:Resource.Error(uiText = UiText.StringResource(R.string.error_unknown))
                 */
                  Resource.Error(
                    uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
                )
            }
        } catch(e: IOException) {
              Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
              Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
     }
}