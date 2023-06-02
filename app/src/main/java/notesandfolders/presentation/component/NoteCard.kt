package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Textsms
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.wear.compose.material.CompactChip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.navigation.Screen
import java.util.UUID

@Composable
fun NoteCard(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    noteId: UUID,
    swipeDismissibleNavController: NavHostController,
) {
    var content = AppDatabase.getDatabase(context).noteDAO().get(noteId).content

    CompactChip(
        onClick = {
            swipeDismissibleNavController.navigate(Screen.Note.route + "/$noteId")
        },
        label = {
            Text (content ?: "")
        },
        modifier = modifier
    )
}