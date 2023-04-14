package com.example.notesandfolders.presentation.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Textsms
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.CompactChip
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

    CompactChip(
        onClick = {},
        label = {
            Text (noteContent)
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Textsms,
                contentDescription = "",
                modifier = iconModifier
            )
        },
        modifier = modifier
    )
}