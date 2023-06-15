package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.*
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
fun FolderScreen(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    folderId: UUID,
    swipeDismissibleNavController: NavHostController
) {
    var folder =  AppDatabase.getDatabase(context).folderDao().get(folderId)
    var subFolders = AppDatabase.getDatabase(context).folderDao().getChildFolders(folderId).filter { folder -> folder.id != UUID(0L, 0L) }
    var notes = AppDatabase.getDatabase(context).noteDAO().getChildNotes(folderId)
    val listState = rememberScalingLazyListState()

    ScalingLazyColumn(
        modifier = modifier,
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        item { Text(folder.title) }
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
        item { CreateNoteButton(context, modifier, iconModifier, folderId) }
        item { CreateFolderButton(context, modifier, iconModifier, folderId) }
        item { DeleteFolderButton(context, modifier, iconModifier, folder, swipeDismissibleNavController) }
    }
}