package com.example.notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.*
import com.example.notesandfolders.R
import com.example.notesandfolders.presentation.*
import java.util.*

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    swipeDismissibleNavController: NavHostController,
    context: Context
) {
    var preferences = PreferenceManager.getDefaultSharedPreferences(context)
    var folderContent = preferences.getStringSet(stringResource(R.string.landing_screen_id), HashSet())

    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        modifier = modifier,
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        item { NoteCard(modifier, iconModifier) }
        item { FolderCard(modifier, iconModifier) }
        item { CreateNoteButton(modifier, iconModifier) }
        item { CreateFolderButton(context, modifier, iconModifier, stringResource(R.string.landing_screen_id)) }
        item {
            Chip(
                onClick = {
                    var folderId = UUID.randomUUID().toString()
                    swipeDismissibleNavController.navigate(Screen.Folder.route + "/$folderId")
                },
                label = {
                    Text(
                        text = "Check folders",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                modifier = modifier
            )
        }
    }
}
