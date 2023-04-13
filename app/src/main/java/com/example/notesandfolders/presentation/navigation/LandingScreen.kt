package com.example.notesandfolders.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.notesandfolders.presentation.CreateNoteButton
import com.example.notesandfolders.presentation.NoteCard
import java.util.*

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    swipeDismissibleNavController: NavHostController
) {
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        modifier = modifier,
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        item { NoteCard(modifier, iconModifier) }
        item { CreateNoteButton(modifier, iconModifier) }
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
