package com.maximcuker.mvvmrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ScrollableColumn(
                modifier = Modifier.fillMaxHeight().fillMaxWidth()
                    .background(color = Color(0xFFF2F2F2))
            ) {
                Image(
                    bitmap = imageFromResource(
                        res = resources,
                        resId = R.drawable.happy_meal_small
                    ),
                    modifier = Modifier.height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Happy Meal",
                            style = TextStyle(
                                fontSize = TextUnit.Sp(26)
                            )
                        )
                        Text(
                            text = "$5.99",
                            style = TextStyle(
                                color = Color.Green,
                                fontSize = TextUnit.Sp(17)
                            ),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "900 calories",
                        style = TextStyle(
                            fontSize = TextUnit.Sp(17)
                        )
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Order now")
                    }
                }
            }
        }

//        setContent {
//            Column() {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp)
//                        .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
//                    verticalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "ITEM1",
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    )
//                    Text(
//                        text = "ITEM2",
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    )
//                }
//                Spacer(modifier = Modifier.padding(20.dp))
//                Row(
//                    modifier = Modifier
//                        .width(200.dp)
//                        .height(200.dp)
//                        .border(border = BorderStroke(width = 1.dp, color = Color.Black)),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "ITEM2",
//                        modifier = Modifier.align(Alignment.CenterVertically)
//                    )
//                }
//
//            }
//
//        }

    }
}