package com.sandbox.ui.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SimpleButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.wrapContentSize(),
        enabled = enabled,
    ) {
        Text(text = text)
    }
}