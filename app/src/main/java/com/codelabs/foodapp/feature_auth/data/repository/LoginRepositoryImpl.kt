package com.codelabs.foodapp.feature_auth.data.repository

import android.content.SharedPreferences
import android.util.Log
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
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
         val request = LoginRequest(email, password)
         val loginRequest = LoginRequestParent(request)
//         val json = JSONObject("{\"LoginRequest\":{\"password\":\"Vamsi@1289\",\"username\":\"vamsi@gmail.com\"}}")
         val response = api.login(loginRequest)
//         response.message?.let { Log.d("Response", it) }
         response?.let { Log.d("Response response", it.toString()) }
         return Resource.Success(Unit)
     }
//        return try{
//            request?.let { Log.d("Request Response", request.username) }
//            val response = api.login(loginRequest)
//            response.message?.let { Log.d("Response", it) }
//            response?.let { Log.d("Response response", it.toString()) }
//            response.successful?.let { Log.d("Response Success", "${response.successful}") }
//            if(response.successful){
//                Log.d("Response if", response.data.toString())
//                response.data?.let { loginResponse ->
//                    Log.d("Response if token", loginResponse.token)
//                    sharedPreferences.edit()
//                        .putString(Constants.KEY_JWT_TOKEN,response.data.toString())
//                        .apply()
//                }
//                Resource.Success(Unit)
//            }
//         else {
//                response.data?.let { Log.d("Response else", it.token) }
//                response.message?.let { msg->
//                    Resource.Error(UiText.DynamicString(msg))
//                }?:Resource.Error(UiText.StringResource(R.string.error_unknown))
//            }
//        } catch(e: IOException) {
//            e.message?.let { Log.d("Response 2", it) }
//            e.printStackTrace()
//            Resource.Error(
//                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
//            )
//        } catch(e: HttpException) {
//            e.message?.let { Log.d("Response 3", it) }
//            e.code()?.let { Log.d("Response 4", e.response().toString()) }
//            e.response()?.message()?.let { Log.d("Response 5", it.toString()) }
//            e.printStackTrace()
//            Resource.Error(
//                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
//            )
//        }
//    }
}