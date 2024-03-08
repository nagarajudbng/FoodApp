package com.codelabs.foodapp.feature_prod.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.foodapp.MenuList
import com.codelabs.foodapp.core.presentation.PagingState
import com.codelabs.foodapp.core.presentation.UiEvent
import com.codelabs.foodapp.core.util.Resource
import com.codelabs.foodapp.feature_prod.domain.usecases.ProductUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MenuViewModel constructor (
  private val productUseCase: ProductUseCase
): ViewModel() {

    private val _eventFLow = MutableSharedFlow<UiEvent>()
    val eventFlow  = _eventFLow.asSharedFlow()

    private val _productState = mutableStateOf(ProductUiState())
    val productState:State<ProductUiState> = _productState

    private val _pagingState = mutableStateOf<PagingState<MenuList>>(PagingState())
    val pagingState:State<PagingState<MenuList>> = _pagingState
    init {
        this.getProducts()
    }
    data class ProductUiState(
        val isLoading: Boolean = true,
        val productList: MenuList? = null
    )
     private fun getProducts(){
        viewModelScope.launch {
            pagingState.value.isLoading = true
            when(val response = productUseCase.getProducts()){
                is Resource.Success->{
                    _productState.value = productState.value.copy(
                        productList = response.data
                    )
                    pagingState.value.isLoading = false
                }
                is Resource.Error->{

                }
            }
        }

    }
}