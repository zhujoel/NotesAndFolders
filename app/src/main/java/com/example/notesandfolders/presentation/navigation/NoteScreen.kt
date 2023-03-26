package com.example.notesandfolders.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text

@Composable
fun NoteScreen(
    modifier: Modifier = Modifier
) {
    ScalingLazyColumn(modifier = modifier){
        item {
            Text(
                textAlign = TextAlign.Center,
                text = "You are now in the note screen"
            )
        }
    }
}
