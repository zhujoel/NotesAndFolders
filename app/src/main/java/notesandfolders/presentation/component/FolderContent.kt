package notesandfolders.presentation.component

import android.content.Context
import android.widget.EditText
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import notesandfolders.entities.AppDatabase
import java.util.UUID
import androidx.wear.compose.material.Text
import notesandfolders.entities.Folder
import notesandfolders.entities.Note

@Composable
fun FolderContent(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier,
    folderId: UUID,
    swipeDismissibleNavController: NavHostController
) {
    var title =  AppDatabase.getDatabase(context).folderDao().get(folderId).title
    var subFolders = AppDatabase.getDatabase(context).folderDao().getChildFolders(folderId).filter { folder -> folder.id != UUID(0L, 0L) }
    var notes = AppDatabase.getDatabase(context).noteDAO().getChildNotes(folderId)

    Column(modifier = modifier) {
        Text(title ?: "")
        for (subFolder in subFolders){
            FolderCard(context, modifier, iconModifier, subFolder.id, swipeDismissibleNavController)
        }
        for (note in notes){
            NoteCard(context, modifier, iconModifier, note.id, swipeDismissibleNavController)
        }
        CreateNoteButton(context, modifier, iconModifier, folderId)
        CreateFolderButton(context, modifier, iconModifier, folderId)
    }
}