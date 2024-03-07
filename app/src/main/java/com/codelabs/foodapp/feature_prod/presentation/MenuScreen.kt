package com.codelabs.foodapp.feature_prod.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelabs.foodapp.Categories
import com.codelabs.foodapp.ItemList
import com.codelabs.foodapp.Subcategories
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
//    MenuScreen(navController = NavHostController1,content = categories)
}

@Composable
fun MenuScreen(
    navController: NavHostController1,
    content:List<Categories>
) {
//    Column(
//        Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Button(onClick = {
//            navController.popBackStack()
//        }) {
//            Text("Go to Previous Screen")
//        }
//    }
    ProductScreenContent(content)
}
@Preview
@Composable
fun ProductScreenContentPreview(){
    ProductScreenContent(content = categories
    )
}
@Composable
fun ProductScreenContent(
    content:List<Categories>
){
    Surface(modifier = Modifier
        .fillMaxSize()
    ){
        Column(modifier = Modifier
            .padding(bottom = 50.dp)
        ) {
            SearchBar(Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(5.dp))
            ProductList(categories.get(0).subcategories.get(0).itemList)
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom
        ){
            CategoryList(modifier = Modifier,categories)
        }
    }
}
@Composable
fun CategoryList(
    modifier : Modifier = Modifier,
    categories: List<Categories>
) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .background(Color.White)
        ) {
            items(categories) { item ->
                CategoryItem(item)
            }
        }
}
@Composable
fun ProductList(
    content:List<ItemList>
){
    Column (
        modifier = Modifier
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 8.dp),
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(content) { item ->
                FoodItem(item)
            }
        }
    }

}
