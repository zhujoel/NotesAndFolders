package com.example.notesandfolders.presentation.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text

@Composable
fun NoteCard(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    noteId: String
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var noteContent = preferences.getString(noteId, "") ?: ""


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
        title = { Text(noteContent) },
        onClick = { /* ... */ }
    ) {
        Text ("On my way!")
    }
}