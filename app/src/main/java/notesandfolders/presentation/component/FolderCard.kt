package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
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
    var title = AppDatabase.getDatabase(context).folderDao().get(folderId).title

    CompactChip(
        onClick = {
            swipeDismissibleNavController.navigate(Screen.Folder.route + "/$folderId")
        },
        label = {
            Text (title ?: "")
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