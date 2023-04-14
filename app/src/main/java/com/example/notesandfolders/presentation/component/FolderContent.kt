package com.example.notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import java.util.HashSet


@Composable
fun FolderContent(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier,
    folderId: String,
    swipeDismissibleNavController: NavHostController,
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var documentsId = preferences.getStringSet(folderId, HashSet<String>()) ?: HashSet()

    Column(modifier = modifier){
        for(id in documentsId){
            if (id.startsWith("folder_")){
                FolderCard(context, modifier, iconModifier, id, swipeDismissibleNavController)
            }
            else if (id.startsWith("note")) {
                NoteCard(context, modifier, iconModifier, id)
            }
        }

        CreateNoteButton(context, modifier, iconModifier, folderId)
        CreateFolderButton(context, modifier, iconModifier, folderId)
    }
}