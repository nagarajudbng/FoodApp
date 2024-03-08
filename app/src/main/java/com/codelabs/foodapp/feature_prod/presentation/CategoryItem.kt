package com.codelabs.foodapp.feature_prod.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import com.codelabs.foodapp.R

@Preview
@Composable
fun CategoryItemPreview(){
//    CategoryItem(categories.get(0), {
//
//    }, i)
}
@Composable
fun CategoryItem(
    categoryName: String,
    selectedCatName: String,
    onCategoryClick: (String) -> Unit
) {
    OutlinedButton(
        modifier = Modifier,
        onClick = {
                onCategoryClick(categoryName)
        },
        border = BorderStroke(1.dp, colorResource(id = R.color.ligh_grey)),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (categoryName == selectedCatName) {
                colorResource(
                    id = R.color.ligh_grey
                )
            } else {
                colorResource(
                    id = R.color.ligh_grey
                ).copy(alpha = 0.2f)
            }
        )
    ) {
            Text(
                text = categoryName,
                modifier = Modifier ,
                fontSize =12.sp,
                color = Color.Black

            )
    }
}