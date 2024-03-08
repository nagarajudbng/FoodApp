package com.codelabs.foodapp.feature_prod.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelabs.foodapp.Categories
import com.codelabs.foodapp.ItemList
import com.codelabs.foodapp.R
import com.codelabs.foodapp.Subcategories
import com.codelabs.foodapp.feature_prod.domain.usecases.ProductUseCase
import kotlinx.coroutines.Dispatchers
import androidx.navigation.NavHostController as NavHostController1

var itemList = arrayListOf<ItemList>(
    ItemList("1","a"),
    ItemList("2","B"),
    ItemList("3","C"),
    ItemList("4","D"),
    ItemList("5","E")
    )

var subCategoriesList = arrayListOf<Subcategories>(
    Subcategories("Sub_A", itemList),
    Subcategories("Sub_B",itemList),
    Subcategories("Sub_C",itemList),
    Subcategories("Sub_D",itemList),
    Subcategories("Sub_E",itemList)
)
var categories:List<Categories> = arrayListOf<Categories>(
    Categories("Category_A", subCategoriesList),
    Categories("Category_B",subCategoriesList),
    Categories("Category_C",subCategoriesList),
    Categories("Category_D",subCategoriesList),
    Categories("Category_E",subCategoriesList)
)

@Preview
@Composable
fun MenuScreenPreview(){
    MenuScreen(
        modifier = Modifier.background(colorResource(id = R.color.list_background)),
        rememberNavController(),
        MenuViewModel(
            ProductUseCase(
                LocalContext.current.applicationContext
            ),
            Dispatchers.Default
        ),
        categories)
}

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier.background(colorResource(id = R.color.pink)),
    navController: NavHostController1,
    viewModel: MenuViewModel,
    content:List<Categories>
) {
    val uiState by viewModel.uiState.collectAsState(ProductScreenUiState())
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxSize()

        ) {

            if (!uiState.productUiState.isLoading) {
                Log.d("UI Render","Loading")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Log.d("UI Render","Products")
                ProductScreenContent(uiState)
            }
        }
    }
}

@Preview
@Composable
fun ProductScreenContentPreview(){
//    ProductScreenContent(
////        content = categories
//    )
}
@Composable
fun ProductScreenContent(
    uiState: ProductScreenUiState
){
    var allKeyword = stringResource(id = R.string.all)
    var selectedCatName by rememberSaveable {
        mutableStateOf(allKeyword)
    }
    Column(
        modifier = Modifier
            .padding(bottom = 50.dp)
    ) {
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(5.dp))
        ProductList(
            uiState.productUiState.productList,
            selectedCatName = selectedCatName,
            allCatText = stringResource(id = R.string.all))
    }
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        CategoryList(
            modifier = Modifier,
            uiState.categoryUIState.categoryList,
            selectedCatName,
            onCategoryClick = remember { { selectedCatName=it } }
        )
    }
}
@Composable
fun CategoryList(
    modifier : Modifier = Modifier,
    categories: List<Categories>,
    selectedCatName: String,
    onCategoryClick: (String) -> Unit
) {
        LazyRow(

            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .padding(start = 5.dp)
                .background(Color.White)
        ) {
            item {
                CategoryItem(
                    categoryName = stringResource(id = R.string.all),
                    selectedCatName = selectedCatName,
                    onCategoryClick = onCategoryClick
                )
            }
            items(categories) { item ->
                CategoryItem(
                    categoryName = item.name?:"--",
                    selectedCatName = selectedCatName,
                    onCategoryClick = onCategoryClick
                )
            }
        }
}
@Composable
fun ProductList(
    productList: List<ItemList>,
    selectedCatName: String,
    allCatText:String
){
    Column (
        modifier = Modifier

    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .background(colorResource(id = R.color.list_background))
                .padding(horizontal = 8.dp),
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                if(selectedCatName == allCatText)
                 productList
                else
                 productList.filter { it.category?.uppercase() == selectedCatName.uppercase() }
            ) { item ->
                FoodItem(item)
            }
        }
    }

}
