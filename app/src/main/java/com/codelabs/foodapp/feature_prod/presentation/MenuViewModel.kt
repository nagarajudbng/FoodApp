package com.codelabs.foodapp.feature_prod.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.foodapp.Categories
import com.codelabs.foodapp.ItemList
import com.codelabs.foodapp.R
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.core.util.UiText
import com.codelabs.foodapp.feature_prod.domain.usecases.ProductUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MenuViewModel constructor (
    private val productUseCase: ProductUseCase,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableSharedFlow<ProductScreenUiState>()
    val uiState: SharedFlow<ProductScreenUiState> = _uiState.asSharedFlow()

    init {
        this.getProducts()
    }

     private fun getProducts(){
        viewModelScope.launch(ioDispatcher) {
            when(val response = productUseCase.getProducts()){
                is Resource.Success->{

//                    response.data?.MenuListResonse?.menuItemList?.categories?.get(0)?.subcategories?.get(0)?.itemList as List<ItemList>


                    _uiState.emit(
                        ProductScreenUiState(
                            categoryUIState = CategoryUIState(
                                isLoading = false,
                                categoryList = response.data?.MenuListResonse?.menuItemList?.categories
                                    ?: listOf()
                            ),
                            productUiState = ProductUiState(
                                isLoading = false,
                                productList=getProducts(response.data?.MenuListResonse?.menuItemList?.categories)
                            )
                    )
                    )
                }
                is Resource.Error->{
                    _uiState.emit(
                        ProductScreenUiState(
                            productUiState = ProductUiState(isLoading = false),
                            errorMessage = listOf(
                                UiText.StringResource(id=R.string.error_unknown)
                            )
                    )
                    )
                }
            }
        }

    }
    fun getProducts(allList: ArrayList<Categories>?): List<ItemList> {
        val prod: ArrayList<ItemList> = ArrayList()
        if (allList != null) {
            for (cat in allList) {
                for (sub in cat.subcategories) {
                    for (item in sub.itemList) {
                        prod.add(item)
                    }
                }
            }
        }
        return prod as List<ItemList>
    }
}

data class ProductScreenUiState(
    val categoryUIState:CategoryUIState= CategoryUIState(),
    val productUiState:ProductUiState = ProductUiState(),
    val errorMessage:List<UiText> = listOf()
    )
data class ProductUiState(
    val isLoading: Boolean = true,
    val productList: List<ItemList> = listOf()
)
data class CategoryUIState(
    val isLoading:Boolean = true,
    val categoryList:List<Categories> = listOf()
)