package com.example.notesandfolders.presentation.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.*

@Composable
fun FolderCard(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: String
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var folderTitle = preferences.getString(folderId, "Folder") ?: "Folder"

    CompactChip(
        onClick = {},
        label = {
            Text (folderTitle)
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Folder,
                contentDescription = "",
                modifier = iconModifier
            )
        },
        modifier = modifier
    )
}