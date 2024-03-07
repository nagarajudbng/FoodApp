package com.codelabs.foodapp.feature_prod.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelabs.foodapp.Categories
import com.codelabs.foodapp.ItemList
import com.codelabs.foodapp.R
import com.codelabs.foodapp.Subcategories

@Preview
@Composable
fun CategoryItemPreview(){
    CategoryItem(categories.get(0))
}
@Composable
fun CategoryItem(item: Categories) {
    OutlinedButton(
        modifier = Modifier,
        onClick = {

        },
        border = BorderStroke(1.dp, colorResource(id = R.color.ligh_grey)),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colorResource(
                id = R.color.ligh_grey
            )
        )
    ) {
        Text(
            text = "ADD",
            modifier = Modifier ,
            fontSize =18.sp,
            color = Color.Black

        )
    }
}