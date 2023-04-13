package com.example.notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import java.util.*

@Composable
fun FolderScreen(
    context: Context,
    modifier: Modifier = Modifier,
    folderId: String,
    swipeDismissibleNavController: NavHostController
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)


    ScalingLazyColumn(modifier = modifier){
        item {
            Text(
                textAlign = TextAlign.Center,
                text = "You are now in the folder screen, $folderId",
            )
        }

        item {
            Chip(
                onClick = {
                    var noteId = UUID.randomUUID().toString()
                    swipeDismissibleNavController.navigate(Screen.Note.route + "/$noteId")
                },
                label = {
                    Text(
                        text = "Check note",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                modifier = modifier
            )
        }
    }
}