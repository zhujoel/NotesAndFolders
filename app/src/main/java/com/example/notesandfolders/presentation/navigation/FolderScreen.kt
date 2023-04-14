package com.example.notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import androidx.wear.compose.material.*
import com.example.notesandfolders.presentation.component.FolderContent
import java.util.*

@Composable
fun FolderScreen(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: String,
    swipeDismissibleNavController: NavHostController
) {
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        modifier = modifier,
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        item {
            FolderContent(context, modifier, iconModifier, folderId, swipeDismissibleNavController)
        }
    }
}