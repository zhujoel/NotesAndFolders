package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.component.DeleteNoteButton
import java.util.UUID

@Composable
fun NoteScreen(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier,
    noteId: UUID,
    swipeDismissibleNavController: NavHostController
) {
    var note = AppDatabase.getDatabase(context).noteDAO().get(noteId)

    ScalingLazyColumn(modifier = modifier){
        item {
            Text(note.content)
        }
        item {
            DeleteNoteButton(context, modifier, iconModifier, note, swipeDismissibleNavController)
        }
    }
}