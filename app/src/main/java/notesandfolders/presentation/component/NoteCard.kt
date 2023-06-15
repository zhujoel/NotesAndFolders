package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Textsms
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.CompactChip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.navigation.Screen
import java.util.UUID

@Composable
fun NoteCard(
    context: Context,
    modifier: Modifier = Modifier,
    noteId: UUID,
    swipeDismissibleNavController: NavHostController,
) {
    var note = AppDatabase.getDatabase(context).noteDAO().get(noteId)

    Card(
        onClick = {
            swipeDismissibleNavController.navigate(Screen.Note.route + "/$noteId")
        },
        content = {
            Text (
                note.content,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier,
    )
}