package com.codelabs.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.codelabs.foodapp.feature_auth.data.remote.response.LoginResponseParent
import com.codelabs.foodapp.navigation.AppNavHost
import com.codelabs.foodapp.ui.theme.FoodAppTheme
import com.codelabs.foodapp.util.test.ApiUtils.apiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    loginAuthendication()
                    val navController = rememberNavController()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

fun loginAuthendication() {
    try {
        val json = JSONObject("{\"LoginRequest\":{\"password\":\"Vamsi@1289\",\"username\":\"vamsi@gmail.com\"}}")
//        json.put("email", eMail)
//        json.put("password", ePass)
        val requestBody: RequestBody = RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())
        val call: Call<JSONObject> = apiService.registrationPost(json)
        call.enqueue(
            object : Callback<JSONObject> {
                override fun onResponse(call: Call<JSONObject>?, response: Response<JSONObject>?) {
                    if (response!!.isSuccessful) {

                        //finishAndRemoveTask()
                    } else {

                    }
                }
                override fun onFailure(call: Call<JSONObject>?, t: Throwable?) {

                }
            })
    } catch (e: Exception) {
    }
}



@Composable
private fun Login() {

}
@Composable
private fun Menu() {

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodAppTheme {
        Greeting("Android")
    }
}