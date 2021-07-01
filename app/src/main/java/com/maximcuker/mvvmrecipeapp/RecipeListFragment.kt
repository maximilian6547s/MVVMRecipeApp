package com.maximcuker.mvvmrecipeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import java.nio.file.WatchEvent


class RecipeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            Column(
                modifier = Modifier
                    .border(border = BorderStroke(1.dp, Color.Black))
                    .padding(16.dp)
            ) {
                Text(text = "THIS IS A COMPOSABLE INSIDE A FRAGMENT")
                Spacer(modifier = Modifier.padding(10.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "NEAT")
                Spacer(modifier = Modifier.padding(10.dp))
                val customView = HorizontalProgressBar(ContextAmbient.current) //Way to inflate custom view as a composable
                AndroidView(viewBlock = {customView})
            }
        }
//        val view = ComposeView(requireContext())
//        view.apply {
//            setContent {
//                Text(text = "Hey look a composable")
//            }
//        }
        return view
    }
}