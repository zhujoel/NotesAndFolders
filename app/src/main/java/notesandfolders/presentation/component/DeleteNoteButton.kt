package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.notesandfolders.R
import notesandfolders.entities.AppDatabase
import notesandfolders.entities.Folder
import notesandfolders.entities.Note

@Composable
fun DeleteNoteButton(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier,
    note: Note,
    swipeDismissibleNavController: NavHostController
) {
    Chip(
        onClick = {
            swipeDismissibleNavController.popBackStack()
            AppDatabase.getDatabase(context).noteDAO().delete(note)
        },
        label = {
            Text(
                stringResource(R.string.delete_note_button_label),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = stringResource(R.string.delete_note_button_description),
                modifier = iconModifier
            )
        },
        modifier = modifier
    )
}
