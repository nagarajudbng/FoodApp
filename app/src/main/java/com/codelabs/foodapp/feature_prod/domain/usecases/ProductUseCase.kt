package com.codelabs.foodapp.feature_prod.domain.usecases

import ProductModule
import android.content.Context
import com.codelabs.foodapp.MenuList
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.feature_prod.data.repository.ProductsRepositoryImpl

class  ProductUseCase(app: Context) {

    private val repository : ProductsRepositoryImpl
    init {
        repository = ProductsRepositoryImpl(ProductModule.getApi(app))
    }
    suspend  fun getProducts():Resource<MenuList>{
        return repository.getProductsList()
    }
}