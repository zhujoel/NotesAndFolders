package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.navigation.Screen
import java.util.UUID

@Composable
fun FolderCard(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: UUID,
    swipeDismissibleNavController: NavHostController,
) {
    var folder = AppDatabase.getDatabase(context).folderDao().get(folderId)

    Chip(
        onClick = {
            swipeDismissibleNavController.navigate(Screen.Folder.route + "/$folderId")
        },
        label = {
            Text (
                folder.title,
                overflow = TextOverflow.Ellipsis
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Filled.Folder,
                contentDescription = "",
                modifier = iconModifier
            )
        },
        modifier = modifier,
    )
}