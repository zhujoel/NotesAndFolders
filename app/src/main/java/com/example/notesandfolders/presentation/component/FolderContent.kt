package com.example.notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.Text
import java.util.*

@Composable
fun FolderContent(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier,
    folderId: String,
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var documentsId = preferences.getStringSet(folderId, HashSet<String>()) ?: HashSet()

    Column(modifier = modifier){
        for(id in documentsId){
            if (id.startsWith("folder_")){
                FolderCard(context, modifier, iconModifier, id)
            }
            else if (id.startsWith("note")) {
                NoteCard(context, modifier, iconModifier, id)
            }
        }
    }
}