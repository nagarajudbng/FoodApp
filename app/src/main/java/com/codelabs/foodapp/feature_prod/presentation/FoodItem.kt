package com.codelabs.foodapp.feature_prod.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.codelabs.foodapp.ItemList
import com.codelabs.foodapp.R

@Preview
@Composable
fun FoodItemPreview() {
    FoodItem(ItemList())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItem(
    item:ItemList
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        onClick = {

        },
        colors = CardDefaults.cardColors(containerColor = Color.White)
//        ,
//        border = BorderStroke(
//            1.dp,
//            Brush.horizontalGradient(
//                listOf(
//                    colorResource(id = R.color.dark_green),
//                    colorResource(id = R.color.hunter_green),
//                    colorResource(id = R.color.dark_moss_green),
//                    colorResource(id = R.color.walnut_brown),
//                    colorResource(id = R.color.bole),
//                    colorResource(id = R.color.cordovan),
//                    colorResource(id = R.color.redwood)
//                )
//            )
//        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)

        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                var size by remember { mutableStateOf(Size.Zero) }
                val height: @Composable () -> Dp = {
                    with(LocalDensity.current) {
                        size.height
                            .toDp()
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(.60F)

                        .onGloballyPositioned {
                            it.size
                                .toSize()
                                .also { size = it }
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        var rid = if(item.menutype.equals("VEG")) R.drawable.veg else R.drawable.nonveg
                        Image(
                            painter = painterResource(id = rid ),
                            modifier = Modifier
                                .height(32.dp)
                                .padding(
                                    top = 5.dp,
                                    bottom = 5.dp,
                                    end = 5.dp
                                ),
                            contentDescription = stringResource(id = R.string.app_name)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "BestSeller",
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(colorResource(id = R.color.best_seller), shape = RoundedCornerShape(5.dp))
                                .padding(2.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    item.name?.let {
                        Text(
                            text = it,
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row() {
                        val ratingState = remember { mutableIntStateOf(3) }
                        UserRatingBar(
                            // 2. Customized UserRatingBar
                            ratingState = ratingState,
                            ratingIconPainter = painterResource(id = R.drawable.ic_star_2),
                            size = 20.dp,
                            selectedColor = Color(0xFF5A966E),
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "40 Ratings",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "₹ ${item.price}",
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Gobi Manchurian Balls fried and tossed with outr homamade sauces " +
                                "and diced vegetables",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 12.sp
                    )
                }


                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .fillMaxWidth(.40F),

                    ) {

                    Image(
                        painter = painterResource(id = R.drawable.sample),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(138.dp),
                        contentDescription = stringResource(id = R.string.app_name)
                    )
                    OutlinedButton(

                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(top = 90.dp),
                        onClick = {

                        },
                        border = BorderStroke(1.dp, colorResource(id = R.color.pink)),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = colorResource(
                                id = R.color.light_pink
                            )
                        )
                    ) {
                        Text(
                            text = "ADD",
                            modifier = Modifier,
                            fontSize =18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.pink)
                        )
                    }
                }
            }
        }
    }
}