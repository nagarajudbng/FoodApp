package com.codelabs.foodapp.feature_prod.data.repository

import com.codelabs.foodapp.MenuList
import com.codelabs.foodapp.R
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.core.util.SimpleResource
import com.codelabs.foodapp.core.util.UiText
import com.codelabs.foodapp.feature_prod.data.remote.ProductsAPI
import com.codelabs.foodapp.feature_prod.domain.repository.ProductsRepository
import retrofit2.HttpException
import java.io.IOException

class ProductsRepositoryImpl(
    var api:ProductsAPI
):ProductsRepository {

    override suspend fun getProductsList(): Resource<MenuList> {
        return try{
            val response = api.getProducts()
            Resource.Success(data=response.body())
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