package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipColors
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
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
    Card(
        onClick = {
            swipeDismissibleNavController.popBackStack()
            AppDatabase.getDatabase(context).noteDAO().delete(note)
        },
        content = { Text("Delete") },
        shape = RoundedCornerShape(10.dp)
    )
}
