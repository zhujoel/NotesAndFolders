package com.example.notesandfolders.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.notesandfolders.presentation.CreateNoteButton
import com.example.notesandfolders.presentation.NoteCard

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
                    swipeDismissibleNavController.navigate(Screen.Note.route + "/myNoteId")
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
