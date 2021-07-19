package com.maximcuker.mvvmrecipeapp.presentation.ui.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.maximcuker.mvvmrecipeapp.presentation.components.GenericDialogInfo
import com.maximcuker.mvvmrecipeapp.presentation.components.PositiveAction
import java.util.*

class DialogQueue {
    val queue: MutableState<Queue<GenericDialogInfo>> = mutableStateOf(LinkedList())

    fun  removeHeadMessage() {
        if (queue.value.isNotEmpty()) {
            val update = queue.value
            update.remove() //remove first (oldest message)
            queue.value = ArrayDeque() // force recompose (bug?), can use any another data structure
            queue.value = update
        }
    }

    fun appendErrorMessage(title: String, description: String) {
        queue.value.offer(
            GenericDialogInfo.Builder()
                .title(title)
                .onDismiss(this::removeHeadMessage)
                .description(description)
                .positiveAction(PositiveAction(
                    positiveBtnTxt = "Ok",
                    onPositiveAction = this::removeHeadMessage
                ))
                .build()
        )
    }
}