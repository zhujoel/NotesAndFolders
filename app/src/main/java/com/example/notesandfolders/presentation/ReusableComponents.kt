package com.example.notesandfolders.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*

// Button
@Preview(
    group = "Button",
    widthDp = WEAR_PREVIEW_ELEMENT_WIDTH_DP,
    heightDp = WEAR_PREVIEW_ELEMENT_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)

@Composable
fun CreateNoteButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        // Button
        Button(
            modifier = Modifier.size(ButtonDefaults.ExtraSmallButtonSize),
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add note action",
                modifier = iconModifier
            )
        }
    }
}

// Card
@Preview(
    group = "Card",
    widthDp = WEAR_PREVIEW_ROW_WIDTH_DP,
    heightDp = WEAR_PREVIEW_ROW_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    AppCard(
        modifier = modifier,
        appImage = {
            Icon(
                imageVector = Icons.Rounded.Message,
                contentDescription = "triggers open message action",
                modifier = iconModifier
            )
        },
        appName = { Text("App Name") },
        time = { },
        title = { Text("Title") },
        onClick = { /* ... */ }
    ) {
        Text ("On my way!")
    }
}