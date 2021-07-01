package com.maximcuker.mvvmrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Hey look some text")
                Spacer(modifier = Modifier.padding(top= 10.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "A BUTTON")
                }
            }
        }
    }
}