package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.component.CreateFolderButton
import notesandfolders.presentation.component.CreateNoteButton
import notesandfolders.presentation.component.DeleteFolderButton
import notesandfolders.presentation.component.FolderCard
import notesandfolders.presentation.component.NoteCard
import java.util.UUID


@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    swipeDismissibleNavController: NavHostController,
    context: Context
) {
    var rootFolderId = UUID(0L, 0L)
    var subFolders = AppDatabase.getDatabase(context).folderDao().getChildFolders(rootFolderId)
    var notes = AppDatabase.getDatabase(context).noteDAO().getChildNotes(rootFolderId)
    val listState = rememberScalingLazyListState()

    ScalingLazyColumn(
        modifier = modifier,
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        items(
            count = subFolders.size,
            key = { subFolders[it].id },
            itemContent = {
                    index -> FolderCard(context, modifier, iconModifier, subFolders[index].id, swipeDismissibleNavController)
            })
        items(
            count = notes.size,
            key = { notes[it].id },
            itemContent = {
                    index -> NoteCard(context, modifier, iconModifier, notes[index].id, swipeDismissibleNavController)
            })
        item { CreateNoteButton(context, modifier, iconModifier, rootFolderId) }
        item { CreateFolderButton(context, modifier, iconModifier, rootFolderId) }
    }
}
