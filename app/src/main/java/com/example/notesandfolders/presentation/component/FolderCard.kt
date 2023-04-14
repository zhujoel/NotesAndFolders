package com.example.notesandfolders.presentation.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.*
import com.example.notesandfolders.presentation.navigation.Screen
import java.util.*

@Composable
fun FolderCard(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: String,
    swipeDismissibleNavController: NavHostController,
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var folderTitle = preferences.getString("title_$folderId", "Folder") ?: "Folder"

    CompactChip(
        onClick = {
            swipeDismissibleNavController.navigate(Screen.Folder.route + "/$folderId")
        },
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