package com.codelabs.foodapp.feature_prod.data.remote

import com.codelabs.foodapp.MenuList
import retrofit2.Response
import retrofit2.http.GET

interface ProductsAPI {
    companion object {
        const val BASE_URL = "http://ec2-3-111-157-50.ap-south-1.compute.amazonaws" +
                ".com:8080/highwayhelper/"
    }
    @GET("getMenuList/65155432637d335d4e5753c5")
    suspend fun getProducts():Response<MenuList>


}