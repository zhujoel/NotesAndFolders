package com.example.notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.notesandfolders.R
import com.example.notesandfolders.presentation.*
import com.example.notesandfolders.presentation.component.CreateFolderButton
import com.example.notesandfolders.presentation.component.CreateNoteButton
import com.example.notesandfolders.presentation.component.FolderContent
import java.util.UUID


@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    swipeDismissibleNavController: NavHostController,
    context: Context
) {
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        modifier = modifier,
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        item { FolderContent(context, modifier, iconModifier, stringResource(R.string.landing_screen_id), swipeDismissibleNavController) }
    }
}
