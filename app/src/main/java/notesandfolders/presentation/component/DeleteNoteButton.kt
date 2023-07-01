package notesandfolders.presentation.component

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.Text
import notesandfolders.entities.AppDatabase
import notesandfolders.entities.Note

@Composable
fun DeleteNoteButton(
    context: Context,
    note: Note,
    swipeDismissibleNavController: NavHostController,
    backgroundPainter: Painter,
    shape: Shape
) {
    Card(
        onClick = {
            swipeDismissibleNavController.popBackStack()
            AppDatabase.getDatabase(context).noteDAO().delete(note)
        },
        content = { Text("Delete") },
        shape = shape,
        backgroundPainter = backgroundPainter
    )
}
