package com.example.notesandfolders.presentation.navigation

import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.input.RemoteInputIntentHelper
import com.example.notesandfolders.presentation.wearableExtender

@Composable
fun NoteScreen(
    context: Context,
    modifier: Modifier = Modifier,
    noteId: String
) {
    ScalingLazyColumn(modifier = modifier){
        item {
            Text(
                textAlign = TextAlign.Center,
                text = "You are now in the note screen, $noteId",
            )
        }
    }
}