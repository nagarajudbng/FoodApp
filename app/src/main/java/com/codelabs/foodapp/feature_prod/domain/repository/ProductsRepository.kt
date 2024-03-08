package com.codelabs.foodapp.feature_prod.domain.repository

import com.codelabs.foodapp.MenuList
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.core.util.SimpleResource

interface ProductsRepository {
    suspend fun getProductsList() : Resource<MenuList>
}