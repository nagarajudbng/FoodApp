package com.codelabs.foodapp.util.test

object ApiUtils {


        val BASE_URL = "http://ec2-3-111-157-50.ap-south-1.compute.amazonaws" +
                ".com:8080/highwayhelper/"
    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(APIService::class.java)
}