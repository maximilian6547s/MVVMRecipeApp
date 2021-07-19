package com.maximcuker.mvvmrecipeapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.lang.NullPointerException

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction? = null,
    negativeAction: NegativeAction? = null,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = {
            if (description != null) {
                Text(text = description)
            }
        },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                if (negativeAction != null) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        colors = buttonColors(backgroundColor = MaterialTheme.colors.onError),
                        onClick = negativeAction.onNegativeAction
                    ) {
                        Text(negativeAction.negativeBtnTxt)
                    }
                }
                if (positiveAction != null) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = positiveAction.onPositiveAction
                    ) {
                        Text(positiveAction.positiveBtnTxt)
                    }
                }
            }
        }
    )
}

data class PositiveAction(
    val positiveBtnTxt: String,
    val onPositiveAction: () -> Unit,
)

data class NegativeAction(
    val negativeBtnTxt: String,
    val onNegativeAction: () -> Unit,
)

class GenericDialogInfo
private constructor(builder: Builder) {

    val title: String
    val onDismiss: () -> Unit
    val description: String?
    val positiveAction: PositiveAction?
    val negativeAction: NegativeAction?

    init {
        if (builder.title == null) {
            throw NullPointerException("GenericDialogInfo title cannot be null")
        }
        if (builder.onDismiss == null) {
            throw NullPointerException("GenericDialogInfo onDismiss function cannot be null")
        }
        this.title = builder.title!!
        this.onDismiss = builder.onDismiss!!
        this.description = builder.description
        this.positiveAction = builder.positiveAction
        this.negativeAction = builder.negativeAction
    }

    class Builder {
        var title: String? = null
            private set

        var onDismiss: (() -> Unit)? = null
            private set

        var description: String? = null
            private set

        var positiveAction: PositiveAction? = null
            private set

        var negativeAction: NegativeAction? = null
            private set

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun onDismiss(onDismiss: () -> Unit): Builder {
            this.onDismiss = onDismiss
            return this
        }

        fun description(description: String): Builder {
            this.description = description
            return this
        }

        fun positiveAction(positiveAction: PositiveAction): Builder {
            this.positiveAction = positiveAction
            return this
        }

        fun negativeAction(negativeAction: NegativeAction): Builder {
            this.negativeAction = negativeAction
            return this
        }

        fun build() = GenericDialogInfo(this)
    }
}


